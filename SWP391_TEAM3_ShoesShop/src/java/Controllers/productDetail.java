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

/**
 *
 * @author Admin
 */
public class productDetail extends HttpServlet {
   
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
            out.println("<title>Servlet productDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productDetail at " + request.getContextPath () + "</h1>");
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
        String id=request.getParameter("id");
        int productId;
        Product p;
        try{
            productId=Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            productId=-1;
        }
        if(productId<0){
            p=null;
        }
        else{
            p=prd.getProductById(productId);
        }
        request.setAttribute("p", p);
        if(p!=null){
            request.setAttribute("sizes", prd.getProductSizes(p));
            request.setAttribute("colors", prd.getProductColors(p));
            request.setAttribute("images", prd.getProductImages(p));
            request.setAttribute("ProductStock", prd.getProductStock(p));
        }
        request.getRequestDispatcher("Views/Customer/productDetail.jsp").forward(request, response);
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
        ProductDAO prd = new ProductDAO();
        Integer stock=null;
        PrintWriter o=response.getWriter();
        response.setContentType("text/plain");
        try{
            int productId=Integer.parseInt(request.getParameter("productId"));
            int colorId=Integer.parseInt(request.getParameter("colorId"));
            int sizeId=Integer.parseInt(request.getParameter("sizeId"));
            stock=prd.getStock(productId, colorId, sizeId);
        }
        catch(NumberFormatException e){
            
        }
        if(stock==null){
            o.write("");
        }
        else{
            o.write(stock.toString());
        }
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
