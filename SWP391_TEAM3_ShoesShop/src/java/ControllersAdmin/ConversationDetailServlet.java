/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersAdmin;

import DAL.AccountDAO;
import DAL.ConversationDAO;
import DAL.DBContext;
import DAL.MessageDAO;
import Models.Account;
import Models.Conversation;
import Models.Message;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author biggp
 */
@WebServlet(name = "ConversationDetailServlet", urlPatterns = {"/ConversationDetailServlet"})
public class ConversationDetailServlet extends HttpServlet {

    private ConversationDAO conversationDAO;
    private AccountDAO accountDAO;
    private MessageDAO messageDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        DBContext dbContext = new DBContext();
        this.conversationDAO = new ConversationDAO(dbContext.getConnection());
        this.accountDAO = new AccountDAO();
        this.messageDAO = new MessageDAO(dbContext.getConnection());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra quyền truy cập
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (session.getAttribute("acc") == null || acc.getRoleID() > 2) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login");
            return;
        }

        // Lấy ID cuộc trò chuyện từ request
        int conversationId = Integer.parseInt(request.getParameter("id"));

        // Lấy cuộc trò chuyện và thông tin tin nhắn
        Conversation conversation = conversationDAO.getConversationByConversationId(conversationId);
        List<Message> messages = messageDAO.getMessagesByConversationId(conversationId);

        // Lấy thông tin khách hàng (Account)
        Account customer = accountDAO.getAccountById(conversation.getCustomerId());
        Account staff = accountDAO.getAccountByID(acc.getAccountID());
        session.setAttribute("accountID", staff.getAccountID());
        session.setAttribute("roleID", staff.getRoleID());

        // Truyền dữ liệu vào request
        request.setAttribute("conversation", conversation);
        request.setAttribute("messages", messages);
        request.setAttribute("customer", customer);
        request.setAttribute("staff", staff);

        // Forward đến JSP
        request.getRequestDispatcher("Views/Staff/ConversationDetail.jsp").forward(request, response);
    }
}
