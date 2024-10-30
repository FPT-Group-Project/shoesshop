/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.FeedbackDAO;
import Models.Account;
import Models.Feedback;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import java.util.Date;


/**
 *
 * @author thanh
 */
@WebServlet(name = "SubmitFeedbackServlet", urlPatterns = {"/SubmitFeedbackServlet"})
public class SubmitFeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session = request.getSession();

        Account acc = (Account) session.getAttribute("acc");

        // Kiểm tra nếu acc là null
        if (acc == null) {
            // Chuyển hướng về trang đăng nhập
            response.sendRedirect("login"); // Thay "login.jsp" bằng đường dẫn đúng đến trang đăng nhập của bạn
            return; // Kết thúc phương thức
        }

        // Nếu acc không phải là null, lấy accountId
        Integer accountId = acc.getAccountID();

        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        

        Date feedbackDate = new Date(); // Gán ngày hiện tại

        Feedback feedback = new Feedback(accountId, productId, rating, comment, feedbackDate);
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        boolean success = feedbackDAO.addFeedback(feedback);

        response.sendRedirect("productDetail?id=" + productId);
    }
}
