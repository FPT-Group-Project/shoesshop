/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import DAL.ConversationDAO;
import DAL.DBContext;
import DAL.MessageDAO;
import Models.Account;
import Models.Conversation;
import Models.Message;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "CustomerChatServlet", urlPatterns = {"/CustomerChatServlet"})
    public class CustomerChatServlet extends HttpServlet {

    private ConversationDAO conversationDAO;
    private MessageDAO messageDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        DBContext dbContext = new DBContext(); // Khởi tạo DBContext
        this.messageDAO = new MessageDAO(dbContext.getConnection()); // Truyền kết nối vào MessageDAO
        this.conversationDAO = new ConversationDAO(dbContext.getConnection()); // Khởi tạo ConversationDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("acc") == null) {
        response.sendRedirect("login");
        return;
    }

    // Lấy accountId từ session
    Account account = (Account) session.getAttribute("acc");
    int accountId = account.getAccountID();

    // Kiểm tra nếu đã có Conversation cho customer này
    Conversation conversation = conversationDAO.getConversationByCustomerId(accountId);
    if (conversation == null) {
        // Nếu chưa có, tạo Conversation mới
        conversationDAO.createConversation(accountId);
        conversation = conversationDAO.getConversationByCustomerId(accountId);
    }

    // Lấy ConversationID và danh sách tin nhắn nếu có cuộc trò chuyện
    int conversationId = conversation.getConversationId();
    List<Message> messages = messageDAO.getMessagesByCustomerId(accountId);

    // Truyền thông tin cần thiết vào request
    request.setAttribute("messages", messages);
    request.setAttribute("customer", account);
    request.setAttribute("conversationId", conversationId);

    // Chuyển hướng tới trang CustomerChat.jsp
    request.getRequestDispatcher("Views/Customer/CustomerChat.jsp").forward(request, response);
}
}
