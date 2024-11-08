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


@WebServlet(name = "AddWishlist", urlPatterns = {"/addWishlist"})
public class AddWishlist extends HttpServlet {
    @Override

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession();
    Account acc = (Account) session.getAttribute("acc");

    if (acc == null) {
        // Redirect to login page if user is not logged in
        response.sendRedirect("login");
        return;
    }

    Integer accountId = acc.getAccountID();
    String productIdParam = request.getParameter("id");
    if (productIdParam == null || productIdParam.isEmpty()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
        return;
    }

    int productId;
    try {
        productId = Integer.parseInt(productIdParam);
    } catch (NumberFormatException e) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID");
        return;
    }

   WishlistDAO wishlistDAO = new WishlistDAO();
    try {
        boolean added = wishlistDAO.addProductToWishlist(accountId, productId);
        if (added) {
            request.setAttribute("successMessage", "Product has been successfully added to the wishlist.");
        } else {
            request.setAttribute("errorMessage", "The product is already in the wishlist.");
        }
    } catch (SQLException e) {
        request.setAttribute("errorMessage", "An error occurred while adding the product to the wishlist.");
        e.printStackTrace();
    }

    // Forward request to home.jsp with the message attributes
    request.getRequestDispatcher("products?page=1").forward(request, response);
}
}