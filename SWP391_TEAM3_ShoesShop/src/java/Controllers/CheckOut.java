/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;



import DAL.CartDAO;
import DAL.OrderDAO;
import Models.Account;
import Models.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thanh
 */
public class CheckOut extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartDAO cartDAO = new CartDAO();
        //int accountId = 3; //CÓ LOGIN THÌ SỬA ĐOẠN NÀY THÀNH ID CỦA ACCOUNT
         HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            Integer accountId = acc.getAccountID();
//
//        String email = "robert@example.com"; //CÓ LOGIN THÌ SỬA ĐOẠN NÀY THÀNH Email CỦA ACCOUNT
//         
//        String phone = "555444333"; //CÓ LOGIN THÌ SỬA ĐOẠN NÀY THÀNH Phone CỦA ACCOUNT
        String email = acc.getEmail();
        String phone = acc.getPhoneNumber();
        List<Cart> arr = cartDAO.getCartItemsByAccountId(accountId); 
        int total = 0;
        int shipping = 0; //NẾU CÓ SHIPPING THÌ THAY VÀO ĐÂY
        for (int i = 0; i < arr.size(); i++) {
            total += arr.get(i).getProduct().getPrice() * arr.get(i).getQuantity();
        }
        request.setAttribute("total", total);
        request.setAttribute("shipping", shipping);
        request.setAttribute("carts", arr);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.getRequestDispatcher("Views/Customer/checkout.jsp").forward(request, response);
    }

   
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        if(type.equalsIgnoreCase("order")) {
        CartDAO cartDAO = new CartDAO();
//        int accountId = 3; //CÓ LOGIN THÌ SỬA ĐOẠN NÀY THÀNH ID CỦA ACCOUNT
        HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            Integer accountId = acc.getAccountID();
             if (accountId == null) {
                out.println("{\"message\":\"You need to log in before start shopping\", \"status\":\"warning\"}");
                return;
            }


        String email = acc.getEmail();
        String phone = acc.getPhoneNumber();
        double total = Double.parseDouble(request.getParameter("total"));
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        

        List<Cart> carts = cartDAO.getCartItemsByAccountId(accountId);
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(accountId, carts, address, payment, total);
        }
    }
 
}


