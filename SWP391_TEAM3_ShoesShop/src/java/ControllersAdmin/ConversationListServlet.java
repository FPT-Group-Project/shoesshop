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
import java.util.ArrayList;
import java.util.List;
import java.io.PrintWriter;

/**
 *
 * @author biggp
 */
@WebServlet(name = "ConversationListServlet", urlPatterns = {"/ConversationListServlet"})
public class ConversationListServlet extends HttpServlet {

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
        // Kiểm tra quyền truy cập của Staff/ Admin
         // Kiểm tra quyền truy cập của Staff/ Admin
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (session.getAttribute("acc") == null || acc.getRoleID() > 2) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login");
            return;
        } else {
            Integer accountId = acc.getAccountID();

            // Lấy danh sách các cuộc trò chuyện
            List<Conversation> conversations = conversationDAO.getAllConversations();

            // Tạo một danh sách để chứa các đối tượng Account
            List<Account> customers = new ArrayList<>();
            // Tạo một danh sách để chứa các tin nhắn gần nhất
            List<Message> latestMessages = new ArrayList<>();

            // Lấy thông tin khách hàng và tin nhắn gần nhất cho từng cuộc trò chuyện
            for (Conversation conversation : conversations) {
                Account customer = accountDAO.getAccountById(conversation.getCustomerId());
                if (customer != null) {
                    customers.add(customer);  // Thêm đối tượng Account vào danh sách
                }

                // Lấy tin nhắn gần nhất
                Message latestMessage = messageDAO.getLatestMessageByConversationId(conversation.getConversationId());
                latestMessages.add(latestMessage);
            }

            // Truyền danh sách cuộc trò chuyện, khách hàng và tin nhắn vào JSP
            request.setAttribute("conversations", conversations);
            request.setAttribute("customers", customers);  // Truyền danh sách khách hàng vào request
            request.setAttribute("latestMessages", latestMessages); // Truyền danh sách tin nhắn vào request

            request.getRequestDispatcher("Views/Staff/ConversationList.jsp").forward(request, response);
        }
    }
}