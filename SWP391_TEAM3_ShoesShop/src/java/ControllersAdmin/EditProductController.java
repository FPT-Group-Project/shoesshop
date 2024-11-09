/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersAdmin;

import DAL.ListProductAdminDao;
import DAL.BrandDAO;
import DAL.ProductDAO;
import Models.Brand;
import Models.Product;
import Models.ProductAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author biggp
 */
@WebServlet(name = "EditProductController", urlPatterns = {"/editProduct"})
public class EditProductController extends HttpServlet {

    private ProductDAO productDAO = new ProductDAO();
    private BrandDAO brandDAO = new BrandDAO();

@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy productId từ URL
        int productId = Integer.parseInt(request.getParameter("productID"));

        // Tạo DAO để lấy thông tin sản phẩm
        ListProductAdminDao PAD = new ListProductAdminDao();
        
        Product product = PAD.getProductById(productId);
        // Lấy danh sách các thương hiệu từ database
        BrandDAO brandDAO = new BrandDAO();
        List<Brand> brandList = brandDAO.getAllBrands();

        // Đưa thông tin sản phẩm và danh sách thương hiệu vào request
        request.setAttribute("product", product);
        request.setAttribute("brandList", brandList);

        // Chuyển tiếp tới trang editProduct.jsp
        request.getRequestDispatcher("/Views/Admin/editProduct.jsp").forward(request, response);
    }
}
