/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ControllersAdmin;
import java.io.PrintWriter;

import DAL.NewsDao;
import Models.News;
import java.util.List;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import Models.Product;
import DAL.ProductDAO;
import Models.Account;
import Models.Brand;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Tuan anh
 */
@WebServlet(name="EditNewsController", urlPatterns={"/EditNewsController"})
public class EditNewsController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet EditNewsController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditNewsController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String id = request.getParameter("id");
    int idp = (id == null || id.isEmpty()) ? 1 : Integer.parseInt(id);
    NewsDao dao = new NewsDao();
    News newDetail = dao.getNews(idp);

    // Tách nội dung thành mảng String dựa trên ký tự xuống dòng
    String[] contentArray = newDetail.getContent().split("<br />");

    // Nối các phần tử thành một chuỗi với ký tự xuống dòng
    StringBuilder contentBuilder = new StringBuilder();
    for (String content : contentArray) {
        contentBuilder.append(content).append("\n");
    }
    String contentString = contentBuilder.toString().trim(); // Cắt ký tự thừa

    // Gửi dữ liệu đến JSP
    request.setAttribute("newDetail", newDetail);
    request.setAttribute("contentString", contentString);

    request.getRequestDispatcher("/Views/Admin/EditNews.jsp").forward(request, response);
}



    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
   String id = request.getParameter("id");
    int idp = (id == null || id.isEmpty()) ? 1 : Integer.parseInt(id);
    NewsDao dao = new NewsDao();
    News newDetail = dao.getNews(idp);

    // Tách nội dung thành mảng String dựa trên ký tự xuống dòng
    String[] contentArray = newDetail.getContent().split("<br />");

    // Nối các phần tử thành một chuỗi với ký tự xuống dòng
    StringBuilder contentBuilder = new StringBuilder();
    for (String content : contentArray) {
        contentBuilder.append(content).append("\n");
    }
    String contentString = contentBuilder.toString().trim(); // Cắt ký tự thừa

    // Gửi dữ liệu đến JSP
    request.setAttribute("newDetail", newDetail);
    request.setAttribute("contentString", contentString);

    request.getRequestDispatcher("/Views/Admin/EditNews.jsp").forward(request, response);
}

}
