/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;



import DAL.CartDAO;
import Models.Account;
import Models.Cart;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet
public class CartController extends HttpServlet {

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
//        
         HttpSession session = request.getSession();
         Account acc = (Account) session.getAttribute("acc");
          
        
         if (acc == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login");
            return;
        }
  Integer accountId = acc.getAccountID();
  List<Cart> arr = cartDAO.getCartItemsByAccountId(accountId); 
        //System.out.println(arr.size());
         int itemCount = cartDAO.countItemsByAccountId(accountId);  
        
        // Đặt itemCount vào request và chuyển đến trang cart.jsp
        request.setAttribute("itemCount", itemCount);
        request.setAttribute("carts", arr);
        request.getRequestDispatcher("Views/Customer/Cart.jsp").forward(request, response);
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
        try {
       if(request.getParameter("type") != null && request.getParameter("type").equalsIgnoreCase("update")) {
       int quantity = Integer.parseInt(request.getParameter("quantity"));
       int cartId = Integer.parseInt(request.getParameter("cartId"));
        CartDAO cartDAO = new CartDAO();
        boolean result = cartDAO.updateCartQuantity(cartId, quantity);
        //System.out.println(result);
       } else if(request.getParameter("type") != null && request.getParameter("type").equalsIgnoreCase("delete")) {
        CartDAO cartDAO = new CartDAO();
       int cartId = Integer.parseInt(request.getParameter("cartId"));
        cartDAO.removeFromCart(cartId);
       }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 
}


