/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.ProductDAO;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Admin
 */

public class searchProduct extends HttpServlet {
   
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
            out.println("<title>Servlet searchProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet searchProduct at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        ProductDAO prd=new ProductDAO();
        String searchQuery=request.getParameter("searchQuery");
        Integer m1=null,m2=null;
        try{
            String min=request.getParameter("min");
            String max=request.getParameter("max");
            if(min!=null){
                m1=Integer.parseInt(min);
            }
            if(max!=null){
                m2=Integer.parseInt(max);
            }
        }
        catch(NumberFormatException e){
            
        }
        List<String> keywords=Arrays.asList((searchQuery.trim()).split("\\s+"));
        List<Product> searchResult=prd.searchProductByKeywords(keywords);
        List<List<Product>> productPageList=prd.getAllProductsPaginated(searchResult, 12);
        String page=request.getParameter("page");
        int pageNumber;
        try{
            pageNumber=Integer.parseInt(page);
        }
        catch(NumberFormatException e){
            pageNumber=1;
        }
        List<Product> pageContent=productPageList.get(pageNumber-1);
        request.setAttribute("list", pageContent);
        request.setAttribute("pagesNumber", productPageList.size());
        request.setAttribute("pageName", "Shoes Shop");
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
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
