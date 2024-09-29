/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersAdmin;

import DAL.ListProductAdminDao;
import Models.Brand;
import Models.ProductAdmin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tuan anh
 */
@WebServlet(name = "ProductListController", urlPatterns = {"/ProductListController"})
public class ProductListController extends HttpServlet {

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
            out.println("<title>Servlet ProductListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    String pageStr = request.getParameter("page");
    int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
    
    String brand = request.getParameter("brand");
    // Có thể chuyển đổi brand thành ID nếu cần
    int brandid = (brand == null || brand.isEmpty()) ? 1 : Integer.parseInt(brand);
    
    String searchKeyword = request.getParameter("keyW");

    ListProductAdminDao listProductAdmin = new ListProductAdminDao();

    // Khai báo danh sách sản phẩm
    List<ProductAdmin> listP = new ArrayList<>();
    int totalProducts = 0;

    // Kiểm tra từ khóa tìm kiếm
    if (searchKeyword != null && !searchKeyword.isEmpty()) {
        listP = listProductAdmin.getProductListForAdmin(page, brandid, searchKeyword);
        totalProducts = listProductAdmin.countProductListForAdmin(brandid, searchKeyword);
    } else {
        listP = listProductAdmin.getProductListForAdmin(page, brandid);
        totalProducts = listProductAdmin.countProductListForAdmin(brandid);
    }

    // Đếm tổng số sản phẩm
    int totalPages = (int) Math.ceil((double) totalProducts / 5);
    List<Brand> brandDisplay = listProductAdmin.getListBrand();

    // Truyền thông tin cho JSP
    request.setAttribute("brandDisplay", brandDisplay);
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("currentPage", page);
    request.setAttribute("productList", listP);
  

    // Chuyển hướng đến JSP
    request.getRequestDispatcher("Views/Admin/ListProduct.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
