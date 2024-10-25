/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.BrandDAO;
import DAL.ProductDAO;
import Models.Brand;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class displayAllProducts extends HttpServlet {
   
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
            out.println("<title>Servlet displayAllProducts</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet displayAllProducts at " + request.getContextPath () + "</h1>");
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
        String[] checkedBrandValues=request.getParameterValues("brands");
        String fromValue=request.getParameter("from");
        String toValue=request.getParameter("to");
        String page=request.getParameter("page");
        int pageNumber;
        ProductDAO prd=new ProductDAO();
        List<Product> productList=prd.getAllProducts();
        BrandDAO brd=new BrandDAO();
        List<Brand> brandList=brd.getAllBrands();
        //Filter if at least 1 parameter is filled
        if(checkedBrandValues!=null || fromValue!=null || toValue!=null){
            List<Integer> checkedBrandIds= new ArrayList<>();
            Double from=null, to=null;
            if(checkedBrandValues!=null){
                for(int i=0;i<checkedBrandValues.length;i++){
                    try{
                        checkedBrandIds.add(Integer.parseInt(checkedBrandValues[i]));
                    }
                    catch(NumberFormatException e){
                        
                    }
                }
            }
            try{
                from=Double.parseDouble(fromValue);
            }
            catch(NumberFormatException e){
                
            }
            try{
                to=Double.parseDouble(toValue);
            }
            catch(NumberFormatException e){
                
            }
            productList=prd.filterByBrandId(productList, checkedBrandIds);
            productList=prd.filterByPrice(productList,from,to);
            request.setAttribute("checkedBrands", checkedBrandIds);
            request.setAttribute("from", from);
            request.setAttribute("to", to);
        }
        //Divide products into pages
        if(!productList.isEmpty()){
            List<List<Product>> productPageList=prd.getAllProductsPaginated(productList, 12);
            //Set screen to page
            try{
                pageNumber=Integer.parseInt(page);
                if(pageNumber<=0||pageNumber>productPageList.size()){
                    throw new NumberFormatException();
                }
            }

            catch(NumberFormatException e){
                pageNumber=1;
            }
            List<Product> pageContent=productPageList.get(pageNumber-1);
            request.setAttribute("list", pageContent);
            request.setAttribute("pagesNumber", productPageList.size());
            request.setAttribute("atPage", pageNumber);
        }
        request.setAttribute("brands", brandList);
        request.getRequestDispatcher("Views/Customer/homePage.jsp").forward(request, response);
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
