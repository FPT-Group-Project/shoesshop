/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.OrderDAO2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author thanh
 */
@WebServlet(name = "cancelOrder", urlPatterns = {"/cancelOrder"})
public class cancelOrder extends HttpServlet {

   private OrderDAO2 orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO2(/* Add Connection object here */);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderID"));

        // Lấy trạng thái đơn hàng trước khi hủy
        String orderStatus = orderDAO.getOrderStatus(orderId);
        
        // Chỉ cho phép hủy đơn hàng khi trạng thái là "pending"
        if ("pending".equals(orderStatus)) {
            orderDAO.cancelOrder(orderId);
            response.sendRedirect("orderHistory");
        } else {
            // Nếu không thể hủy đơn hàng, chuyển tiếp với thông báo lỗi
            request.setAttribute("errorMessage", "You can only cancel orders in 'Pending' status.");
            request.getRequestDispatcher("orderHistory").forward(request, response);
        }
    }
}