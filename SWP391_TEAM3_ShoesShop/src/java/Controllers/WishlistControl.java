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

@WebServlet(name = "WishlistControl", urlPatterns = {"/WishlistControl"})
public class WishlistControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login");
            return;
        }

        Integer accountId = acc.getAccountID();
        WishlistDAO wishlistDAO = new WishlistDAO();

        try {
            // Lấy danh sách Wishlist của người dùng
            request.setAttribute("wishlist", wishlistDAO.getWishlistByAccountId(accountId));
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Lỗi khi lấy danh sách Wishlist.");
        }

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("Views/Customer/Wishlist.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc == null) {
            // Redirect to login page if user is not logged in
            response.sendRedirect("login");
            return;
        }

        Integer accountId = acc.getAccountID();
        WishlistDAO wishlistDAO = new WishlistDAO();

        // Lấy productId từ yêu cầu
        String productIdStr = request.getParameter("productId");
        if (productIdStr != null) {
            try {
                int productId = Integer.parseInt(productIdStr);

                // Thực hiện xóa sản phẩm khỏi Wishlist
                boolean isRemoved = wishlistDAO.removeProductFromWishlist(accountId, productId);

                // Gửi thông báo phản hồi
                if (isRemoved) {
                    request.setAttribute("successMessage", " Wishlist remove successfully.");
                } else {
                    request.setAttribute("errorMessage", "Can't not find product in Wishlist.");
                }
            } catch (NumberFormatException | SQLException e) {
                request.setAttribute("errorMessage", "Erole");
            }
        }

        // Tải lại danh sách Wishlist sau khi xóa
        try {
            request.setAttribute("wishlist", wishlistDAO.getWishlistByAccountId(accountId));
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "Lỗi khi lấy danh sách Wishlist.");
        }

        // Chuyển tiếp đến trang JSP
        request.getRequestDispatcher("Views/Customer/Wishlist.jsp").forward(request, response);
    }
}
