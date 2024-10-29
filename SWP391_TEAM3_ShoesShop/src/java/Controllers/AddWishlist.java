/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.WishlistDAO;
import Models.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author thanh
 */
@WebServlet(name = "AddWishlist", urlPatterns = {"/addWishlist"})
public class AddWishlist extends HttpServlet {

private static final long serialVersionUID = 1L;
private WishlistDAO wishlistDAO; // Khai báo WishlistDAO
@Override
 public void init() {
        // Khởi tạo WishlistDAO (bạn cần điều chỉnh theo cách khởi tạo của mình)
        wishlistDAO = new WishlistDAO(); // Hoặc bạn có thể sử dụng Dependency Injection nếu có
    }

@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String productIdStr = request.getParameter("id"); // Lấy ID sản phẩm từ yêu cầu
        HttpSession session = request.getSession();
        
        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session.getAttribute("acc") != null) {
            int accountId = ((Account) session.getAttribute("acc")).getAccountID(); // Lấy accountId từ session
            int productId = Integer.parseInt(productIdStr); // Chuyển đổi sang int

            try {
                // Gọi hàm addProductToWishlist
                boolean added = wishlistDAO.addProductToWishlist(accountId, productId);
                if (added) {
                    // Thành công
                    response.sendRedirect(request.getHeader("Referer"));
                } else {
                    // Xử lý khi không thêm được sản phẩm
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Could not add to wishlist.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
            
            }
        } else {
            // Nếu người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login");
        }
    }
}
