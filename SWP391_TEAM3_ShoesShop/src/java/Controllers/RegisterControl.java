/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;
import DAL.AccountDAO;
import Models.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author vh69
 */
public class RegisterControl extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific erroror occurs
     * @throws IOException if an I/O erroror occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginControl</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControl at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific erroror occurs
     * @throws IOException if an I/O erroror occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific erroror occurs
     * @throws IOException if an I/O erroror occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        AccountDAO dao = new AccountDAO();
        String account = dao.checkEmailExist(email);
        Account checkuser = dao.checkAccountExist(user);
        Account phonenum = dao.checkPhoneExist(phone);
        //check format email 
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        
        //check sdt 
         String regex = "^0\\d{9}$";
         Pattern number = Pattern.compile(regex);
        Matcher matcher2 = number.matcher(phone);
        //check 
        if (checkuser != null) {
            request.setAttribute("error", "Username already exists");
            request.setAttribute("name", name);
            request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
        } else if (!matcher.matches()) {
            request.setAttribute("error", "Invalid email format");
            request.setAttribute("user", user);
            request.setAttribute("name", name);
            request.setAttribute("pass", pass);
            request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
        } else if (account != null) {
            request.setAttribute("error", "Email already exists");
            request.setAttribute("user", user);
            request.setAttribute("name", name);
            request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
        } 
        else if (repass != pass){
            request.setAttribute("error", "Repass must be the same as password");
            request.setAttribute("user", user);
            request.setAttribute("name", name);
            request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
        }
        else if (phonenum != null) {
            request.setAttribute("error", "Phone number already exists");
            request.setAttribute("user", user);
            request.setAttribute("name", name);
            request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
        } 
        else if(!matcher2.matches()){ 
            request.setAttribute("error", "Wrong phone number format");
            request.setAttribute("user", user);
            request.setAttribute("name", name);
            request.getRequestDispatcher("Views/Customer/Register.jsp").forward(request, response);
        }
        else {
            dao.signup(user, pass, name, email, phone);
            request.getRequestDispatcher("Views/Customer/Login.jsp").forward(request, response);
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
