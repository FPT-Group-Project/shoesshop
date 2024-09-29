/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;



import DAL.CartDAO;
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

/**
 *
 * @author thanh
 */
public class addToCart extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addToCart</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        // Lấy thông tin từ request
       
        int productId = Integer.parseInt(request.getParameter("productId"));
        int accountId = 3; //CÓ LOGIN THÌ SỬA ĐOẠN NÀY THÀNH ID CỦA ACCOUNT
        int colorId = Integer.parseInt(request.getParameter("colors"));
        int sizeId = Integer.parseInt(request.getParameter("sizes"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        CartDAO cartDAO = new CartDAO();
        // Tạo đối tượng Cart
        Cart cartItem = new Cart();
        cartItem.setProductID(productId);
        cartItem.setAccountID(accountId);
        cartItem.setColorID(colorId);
        cartItem.setSizeID(sizeId);
        cartItem.setQuantity(quantity);

        // Thêm sản phẩm vào giỏ hàng
        boolean result = cartDAO.addToCart(cartItem);

        // Gửi phản hồi về phía client
        //if (result) {
        //    response.getWriter().write("Sản phẩm đã được thêm vào giỏ hàng thành công!");
        //} else {
        //    response.getWriter().write("Lỗi khi thêm sản phẩm vào giỏ hàng.");
        //}
        response.sendRedirect("cart");
    }
 
}


