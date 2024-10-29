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
      int orderId = Integer.parseInt(request.getParameter("orderID"));
    HttpSession session = request.getSession();
    Account acc = (Account) session.getAttribute("acc");

    // Check if the user is authenticated
    if (acc != null) {
        String orderStatus = orderDAO.getOrderStatus(orderId);

        // Kiểm tra nếu trạng thái là "Delivering"
        if ("Delivering".equalsIgnoreCase(orderStatus)) {
            orderDAO.confirmOrder(orderId);
            response.sendRedirect("orderHistory");
        } else {
            // Đặt thông báo lỗi vào request
            request.setAttribute("errorMessage", "Order cannot be confirmed. It must be in 'Delivering' status.");
            // Chuyển hướng tới trang JSP (ví dụ: orderConfirmation.jsp)
            request.getRequestDispatcher("orderHistory").forward(request, response);
        }
    } else {
        response.sendRedirect("login"); // Redirect to login if the user is not authenticated
    }
}
}
