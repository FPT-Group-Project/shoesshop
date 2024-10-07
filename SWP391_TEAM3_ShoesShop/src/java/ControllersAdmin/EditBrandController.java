/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ControllersAdmin;
import Models.Brand;
import DAL.ListBrandDao;
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
@WebServlet(name = "EditBrandController", urlPatterns = {"/EditBrandController"})
public class EditBrandController extends HttpServlet {

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
            out.println("<title>Servlet EditBrandController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditBrandController at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        int idS = Integer.parseInt(id);
        ListBrandDao listBrandDao = new ListBrandDao();
        Brand brand = listBrandDao.getNameBrand(idS);
        request.setAttribute("id",idS);
        request.setAttribute("nameB", brand.getBrandName());
        request.getRequestDispatcher("/Views/Admin/EditBrand.jsp").forward(request, response);
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
    String id = request.getParameter("id");
    int idS = Integer.parseInt(id);
    String newBrandName = request.getParameter("newBrandName");

    ListBrandDao listBrandDao = new ListBrandDao();
    boolean nameExists = false;

    // Kiểm tra thương hiệu đã tồn tại
    List<Brand> listB = listBrandDao.getListBrand();
    for (Brand brand : listB) {
        if (brand.getBrandName().equals(newBrandName)) {
            nameExists = true;
            break;
        }
    }

    // gửi id ,name , mess
    Brand brand = listBrandDao.getNameBrand(idS);
    request.setAttribute("nameB", brand.getBrandName());
    request.setAttribute("id", idS);

    if (nameExists) {         
        request.setAttribute("mess", "Name already exists.");
        request.getRequestDispatcher("/Views/Admin/EditBrand.jsp").forward(request, response);
    } else {
        // Cập nhật tên thương hiệu
        boolean updated = listBrandDao.updateName(newBrandName, idS); 
        if (updated) {
            request.setAttribute("mess", "Name change successful.");
        } else {
            request.setAttribute("mess", "Failed to change the name.");
        }
        
        request.getRequestDispatcher("/Views/Admin/EditBrand.jsp").forward(request, response);
    }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
