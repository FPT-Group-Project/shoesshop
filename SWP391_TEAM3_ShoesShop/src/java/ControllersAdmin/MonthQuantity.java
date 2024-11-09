/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package ControllersAdmin;

import DAL.ListProductAdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author vh69
 */
public class MonthQuantity extends HttpServlet {
   
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
        request.setCharacterEncoding("UTF-8");
        String year_raw = request.getParameter("year");
        int year = (year_raw == null ? 2024 : Integer.parseInt(year_raw));
        ListProductAdminDao dao = new ListProductAdminDao();
        double totalQuantityMonth1 = dao.totalQuantityMonth(1, year);
        double totalQuantityMonth2 = dao.totalQuantityMonth(2, year);
        double totalQuantityMonth3 = dao.totalQuantityMonth(3, year);
        double totalQuantityMonth4 = dao.totalQuantityMonth(4, year);
        double totalQuantityMonth5 = dao.totalQuantityMonth(5, year);
        double totalQuantityMonth6 = dao.totalQuantityMonth(6, year);
        double totalQuantityMonth7 = dao.totalQuantityMonth(7, year);
        double totalQuantityMonth8 = dao.totalQuantityMonth(8, year);
        double totalQuantityMonth9 = dao.totalQuantityMonth(9, year);
        double totalQuantityMonth10 = dao.totalQuantityMonth(10, year);
        double totalQuantityMonth11 = dao.totalQuantityMonth(11, year);
        double totalQuantityMonth12 = dao.totalQuantityMonth(12, year);
        request.setAttribute("totalQuantityMonth1", totalQuantityMonth1);
        request.setAttribute("totalQuantityMonth2", totalQuantityMonth2);
        request.setAttribute("totalQuantityMonth3", totalQuantityMonth3);
        request.setAttribute("totalQuantityMonth4", totalQuantityMonth4);
        request.setAttribute("totalQuantityMonth5", totalQuantityMonth5);
        request.setAttribute("totalQuantityMonth6", totalQuantityMonth6);
        request.setAttribute("totalQuantityMonth7", totalQuantityMonth7);
        request.setAttribute("totalQuantityMonth8", totalQuantityMonth8);
        request.setAttribute("totalQuantityMonth9", totalQuantityMonth9);
        request.setAttribute("totalQuantityMonth10", totalQuantityMonth10);
        request.setAttribute("totalQuantityMonth11", totalQuantityMonth11);
        request.setAttribute("totalQuantityMonth12", totalQuantityMonth12);
        request.setAttribute("year", year);
        request.getRequestDispatcher("Views/Admin/monthQuantity.jsp").forward(request, response);
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
        processRequest(request, response);
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
