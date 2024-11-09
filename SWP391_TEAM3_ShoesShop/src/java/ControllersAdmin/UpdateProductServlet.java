/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ControllersAdmin;

import DAL.ProductDAO;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 *
 * @author biggp
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/updateProduct"})
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lấy productId từ request
        int productId = Integer.parseInt(request.getParameter("productId"));
        
        // Lấy các giá trị khác từ form
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        Part avatarP = request.getPart("avatarP");

        // Xử lý ảnh (nếu có thay đổi)
        String avatarPath = null;
        if (avatarP != null && avatarP.getSize() > 0) {
            avatarPath = "ImageProductAvt/" + avatarP.getSubmittedFileName();
            avatarP.write(getServletContext().getRealPath("") + "/ImageProductAvt/" + avatarP.getSubmittedFileName());
        } else {
            avatarPath = request.getParameter("currentAvatarPath");  // Lấy đường dẫn ảnh cũ nếu không chọn ảnh mới
        }

        // Cập nhật sản phẩm trong cơ sở dữ liệu
        ProductDAO productDAO = new ProductDAO();
        // Tạo đối tượng Product để cập nhật
        Product product = new Product(productId, productName, description, price, brandId, avatarPath);
        productDAO.updateProduct(product);

        // Sau khi cập nhật xong, chuyển hướng về trang quản lý sản phẩm
        response.sendRedirect("ProductListController");
    }
}
