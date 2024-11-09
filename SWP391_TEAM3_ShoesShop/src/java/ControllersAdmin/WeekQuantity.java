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
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author vh69
 */
public class WeekQuantity extends HttpServlet {
   
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
        String month_raw = request.getParameter("month");
        String from_raw = request.getParameter("from");
        String to_raw = request.getParameter("to");

        LocalDate currentDate = LocalDate.now();
        LocalDate startOfWeek = currentDate.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        int startDay = startOfWeek.getDayOfMonth();
        int endDay = endOfWeek.getDayOfMonth();
        int monthValue = startOfWeek.getMonthValue();

        int year = (year_raw == null ? 2024 : Integer.parseInt(year_raw));
        int month = (month_raw == null ? monthValue : Integer.parseInt(month_raw));
        int from = (from_raw == null ? startDay : Integer.parseInt(from_raw));
        int to = (to_raw == null ? endDay : Integer.parseInt(to_raw));

        ListProductAdminDao dao = new ListProductAdminDao();
        double totalQuantity1 = dao.totalQuantityWeek(1, from, to, year, month);
        double totalQuantity2 = dao.totalQuantityWeek(2, from, to, year, month);
        double totalQuantity3 = dao.totalQuantityWeek(3, from, to, year, month);
        double totalQuantity4 = dao.totalQuantityWeek(4, from, to, year, month);
        double totalQuantity5 = dao.totalQuantityWeek(5, from, to, year, month);
        double totalQuantity6 = dao.totalQuantityWeek(6, from, to, year, month);
        double totalQuantity7 = dao.totalQuantityWeek(7, from, to, year, month);
        request.setAttribute("totalQuantity1", totalQuantity1);
        request.setAttribute("totalQuantity2", totalQuantity2);
        request.setAttribute("totalQuantity3", totalQuantity3);
        request.setAttribute("totalQuantity4", totalQuantity4);
        request.setAttribute("totalQuantity5", totalQuantity5);
        request.setAttribute("totalQuantity6", totalQuantity6);
        request.setAttribute("totalQuantity7", totalQuantity7);
        request.setAttribute("year", year);
        request.getRequestDispatcher("Views/Admin/weekQuantity.jsp").forward(request, response);
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
