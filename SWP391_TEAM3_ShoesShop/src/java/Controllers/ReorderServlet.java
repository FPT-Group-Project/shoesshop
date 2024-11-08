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



/**
 *
 * @author thanh
 */
@WebServlet(name = "ReorderServlet", urlPatterns = {"/reorder"})
public class ReorderServlet extends HttpServlet {

   
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderID"));
         HttpSession session = request.getSession();
          Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login");
            return;
        }
        Integer accountId = acc.getAccountID();

        try {
            // Create an instance of your service or data access object
            OrderDAO2 orderService = new OrderDAO2();
            orderService.reorderToCart(orderId, accountId);
            
            // Redirect or notify the user that the reorder was successful
            response.sendRedirect("cart?status=reorder-success");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cart.jsp?status=reorder-failed");
        }
    }
}
