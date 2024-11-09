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
        
        // Giả sử bạn có một lớp để kết nối DB và gọi phương thức getCouponByCodeName
        UsedCouponDAO couponDAO = new UsedCouponDAO(); // Tạo đối tượng DAO để truy vấn
        UsedCoupon coupon = couponDAO.getCouponByCodeName(codeName);
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
        // Lưu kết quả vào request để truyền cho JSP
        if (coupon != null) {
            request.setAttribute("coupon", coupon);
        } else {
            request.setAttribute("error", "Không tìm thấy mã giảm giá");
        }

        // Chuyển hướng sang trang JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart");
        dispatcher.forward(request, response);
    }

}
