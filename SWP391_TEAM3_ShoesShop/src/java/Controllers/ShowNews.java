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
@WebServlet(name="ShowNews", urlPatterns={"/ShowNews"})
public class ShowNews extends HttpServlet {
   
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
            out.println("<title>Servlet ShowNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShowNews at " + request.getContextPath () + "</h1>");
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
    String pageStr = request.getParameter("page");
    int page = (pageStr == null || pageStr.isEmpty()) ? 1 : Integer.parseInt(pageStr);
    
    NewsDao dao = new NewsDao();
    List<News> newsList = dao.getNewsList(page);
    List<News> newsListTop5 = dao.getNewsListTop5();
    
    // Tách nội dung và lấy phần trước dấu xuống dòng đầu tiên
    for (News news : newsList) {
        if (news.getContent() != null) {
            // Tách nội dung theo ký tự xuống dòng và chỉ lấy phần trước ký tự xuống dòng đầu tiên
            String[] contentLines = news.getContent().split("\n", 2); // Tách thành 2 phần
            news.setContent(contentLines[0].trim()); // Cập nhật nội dung bằng phần đầu tiên
        }
    }

    // Tính tổng số trang
    int totalPages = (int) Math.ceil((double) dao.getTotalNewsCount() / 5);
        
    // Gửi dữ liệu đến JSP
    request.setAttribute("totalPages", totalPages);
    request.setAttribute("currentPage", page);
    request.setAttribute("newsList", newsList);
    request.setAttribute("newsListTop5", newsListTop5);
    request.getRequestDispatcher("/Views/Customer/News.jsp").forward(request, response);
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
