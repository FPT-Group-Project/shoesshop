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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author vh69
 */
public class UserProfile extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserProfile at " + request.getContextPath() + "</h1>");
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
        String aid_raw = request.getParameter("id");
        try {
            int aid = Integer.parseInt(aid_raw);
            HttpSession session = request.getSession();
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountById(aid);
            request.setAttribute("account", account);
            request.setAttribute("passAcc", account.getPassWord());
            request.getRequestDispatcher("Views/Customer/Profile.jsp").forward(request, response);
        } catch (Exception e) {
        }

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
        String name = request.getParameter("name");
        String user = request.getParameter("user");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String aid_raw = request.getParameter("id");
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String repass = request.getParameter("repass");
        String button = request.getParameter("update");

        Account account = new Account();
        AccountDAO adao = new AccountDAO();

        try {
            int aid = Integer.parseInt(aid_raw);

            if (button.equals("profile")) {
                account.setFullName(name);
                account.setUserName(user);
                account.setPhoneNumber(phone);
                account.setEmail(email);
                account.setAccountID(aid);
                int checkUpdate = adao.editProfile(account);
                if (checkUpdate != 0) {
                    doGet(request, response);
                } else {
                    System.out.println("error server");
                }
            }
            if (button.equals("password")) {
                int checkChangePass = adao.changePass(newpass, aid);
                if (checkChangePass != 0) {
                    doGet(request, response);
                } else {
                    System.out.println("error server");
                }
            }

        } catch (Exception e) {
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
