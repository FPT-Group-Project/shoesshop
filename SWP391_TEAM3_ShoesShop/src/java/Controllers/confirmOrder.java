/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;


import DAL.OrderDAO2;
import Models.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *
 * @author thanh
 */
@WebServlet(name = "confirmOrder", urlPatterns = {"/confirmOrder"})
public class confirmOrder extends HttpServlet {

   private OrderDAO2 orderDAO;

    @Override
    public void init() throws ServletException {
        // Khởi tạo OrderDAO với kết nối đến cơ sở dữ liệu
        orderDAO = new OrderDAO2(/* Thêm đối tượng Connection tại đây */);
    }

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get orderID from the request
        int orderId = Integer.parseInt(request.getParameter("orderID"));

        // Get the current session
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
         if (acc == null) {
            // Nếu không có tài khoản, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login"); // Thay đổi 'login' thành URL đúng của trang đăng nhập của bạn
            return; // Kết thúc phương thức để tránh thực hiện tiếp
        }
        Integer accountId = acc.getAccountID();

        // Get the current order status
        String orderStatus = orderDAO.getOrderStatus(orderId);
        
        // Check if the order status is 'delivering' (3)
        if ("delivering".equals(orderStatus)) {
            // Confirm the order if status is valid
            orderDAO.confirmOrder(orderId);
            response.sendRedirect("orderHistory");
        } else {
            // If the order status is not valid for confirmation, redirect back with an error message
            request.setAttribute("errorMessage", "You can only confirm orders that are currently being delivered.");
            request.getRequestDispatcher("orderHistory").forward(request, response);
        }
    }
}