/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.OrderDAO2;

import Models.OrderDetail2;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.List;


/**
 *
 * @author thanh
 */
@WebServlet(name = "OrderDetailServlet2", urlPatterns = {"/orderDetail2"})
public class OrderDetailServlet2 extends HttpServlet {

    private OrderDAO2 orderDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAO2(); // Khởi tạo OrderDAO (hoặc sử dụng dependency injection)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id")); // Lấy orderId từ request
        List<OrderDetail2> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId); // Gọi phương thức lấy chi tiết đơn hàng

        request.setAttribute("orderDetails", orderDetails); // Lưu danh sách orderDetails vào request

        RequestDispatcher dispatcher = request.getRequestDispatcher("Views/Customer/orderDetail.jsp"); // Chuyển hướng tới JSP
        dispatcher.forward(request, response);
    }
}
