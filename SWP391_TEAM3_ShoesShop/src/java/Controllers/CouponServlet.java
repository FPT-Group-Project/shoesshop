/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.CartDAO;
import DAL.UsedCouponDAO;
import Models.Account;
import Models.Cart;
import Models.UsedCoupon;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 *
 * @author thanh
 */
@WebServlet(name = "CouponServlet", urlPatterns = {"/getCoupon"})
public class CouponServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String codeName = request.getParameter("codeName");

    // Instantiate DAO classes
    UsedCouponDAO couponDAO = new UsedCouponDAO();
    CartDAO cartDAO = new CartDAO();

    // Get the current session and check if the user is logged in
    HttpSession session = request.getSession();
    Account acc = (Account) session.getAttribute("acc");

    if (acc == null) {
        // Redirect to login page if user is not logged in
        response.sendRedirect("login");
        return;
    }

    Integer accountId = acc.getAccountID();
    List<Cart> arr = cartDAO.getCartItemsByAccountId(accountId);
    int itemCount = cartDAO.countItemsByAccountId(accountId);

    // Set cart details in the request
    request.setAttribute("itemCount", itemCount);
    request.setAttribute("carts", arr);

    // Retrieve the coupon by code name
    UsedCoupon coupon = couponDAO.getCouponByCodeName(codeName);

    // Check if the coupon exists and has quantity > 0
    if (coupon != null && coupon.getQuantity() > 0) {
        // Update the coupon quantity in the database
        couponDAO.updateCouponQuantity(codeName, coupon.getQuantity() - 1);
        request.setAttribute("coupon", coupon);
    } else if (coupon == null) {
        request.setAttribute("error", "Không tìm thấy mã giảm giá");
    } else {
        request.setAttribute("error", "Mã giảm giá đã hết số lượng");
    }

    // Forward to the cart page
    RequestDispatcher dispatcher = request.getRequestDispatcher("cart");
    dispatcher.forward(request, response);
}
}
