/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;


import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAL.NewsDao;
import Models.News;
import java.util.List;

/**
 *
 * @author Tuan anh
 */
@WebServlet(name="NewsDetailController", urlPatterns={"/NewsDetailController"})
public class NewsDetailController extends HttpServlet {
   
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
            out.println("<title>Servlet NewsDetailController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewsDetailController at " + request.getContextPath () + "</h1>");
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
 
    
    // Tách nội dung thành mảng String
    String[] contentArray = newDetail.getContent().split("<br />");

    // Lấy phần tử đầu tiên
    String contentFirst = contentArray.length > 0 ? contentArray[0] : "";

    // Lấy các phần tử còn lại (phần từ index 1 trở đi)
    String[] contentRest = new String[contentArray.length - 1];
    for (int i = 1; i < contentArray.length; i++) {
        contentRest[i - 1] = contentArray[i]; // Chuyển phần còn lại vào mảng mới
    }
 List<News> newsListTop5 = dao.getNewsListTop5();
request.setAttribute("newsListTop5", newsListTop5);
    // Gửi dữ liệu đến JSP
    request.setAttribute("newDetail", newDetail);
    request.setAttribute("contentFirst", contentFirst);
    request.setAttribute("contentRest", contentRest); // Gửi mảng contentRest đến JSP

    request.getRequestDispatcher("/Views/Customer/NewsDetail.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
