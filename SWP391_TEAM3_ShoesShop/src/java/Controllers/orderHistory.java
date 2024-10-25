/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;


import DAL.OrderDAO2;
import Models.Account;
import Models.Feedback;

import Models.Order2;

import Models.OrderDetail2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


import java.util.List;

/**
 *
 * @author thanh
 */
@WebServlet(name = "orderHistory", urlPatterns = {"/orderHistory"})
public class orderHistory extends HttpServlet {

    private OrderDAO2 orderDAO;

    @Override
    public void init() throws ServletException {
        // Khởi tạo OrderDAO (có thể khởi tạo connection ở đây)
        orderDAO = new OrderDAO2();
    }

    @Override
   
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    HttpSession session = request.getSession();
    Account acc = (Account) session.getAttribute("acc");
      if (acc == null) {
            // Chuyển hướng về trang đăng nhập
            response.sendRedirect("login"); // Thay "login.jsp" bằng đường dẫn đúng đến trang đăng nhập của bạn
            return; // Kết thúc phương thức
        }
    Integer accountId = acc.getAccountID();

    // Lấy danh sách đơn hàng theo accountId
    List<Order2> orders = orderDAO.getOrdersByAccountId(accountId);

    // Lặp qua danh sách đơn hàng để lấy chi tiết cho từng đơn hàng
    for (Order2 order : orders) {
        // Giả sử có một phương thức trong OrderDAO để lấy chi tiết đơn hàng
        List<OrderDetail2> orderDetails = orderDAO.getOrderDetailsByOrderId(order.getOrderID());
        order.setOrderDetails(orderDetails); // Giả sử bạn đã có phương thức setOrderDetails trong lớp Order
    }
    
    request.setAttribute("orderList", orders);

    // Chuyển tiếp đến trang JSP
    request.getRequestDispatcher("Views/Customer/orderHistory.jsp").forward(request, response);
}
}