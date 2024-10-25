package ControllersAdmin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import DAL.NewsDao;
import Models.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig // Thêm dòng này để servlet xử lý multipart
@WebServlet(urlPatterns={"/AddNews"})
public class AddNews extends HttpServlet {
   
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
            out.println("<title>Servlet AddNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNews at " + request.getContextPath () + "</h1>");
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
          request.setAttribute("message", "Enter new title");
        request.getRequestDispatcher("/Views/Admin/AddNews.jsp").forward(request, response);
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
  NewsDao dao = new NewsDao();
    // Thiết lập loại content cho response
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
  String content = request.getParameter("content");
    // Process form data
  

    


    String title = request.getParameter("title");
    if (dao.getTitleNews(title)== false) {
        request.setAttribute("message", "This news already exists");
        request.getRequestDispatcher("/Views/Admin/AddNews.jsp").forward(request, response);
        return;
    }
    if (content.isEmpty()) {
        request.setAttribute("message", "This contend null");
        request.getRequestDispatcher("/Views/Admin/AddNews.jsp").forward(request, response);
        return;
    }
  
            content = content.replace("\n", "\\n"); // Thay thế '\n' bằng '\\n'

    String author = request.getParameter("author");
    String videoLink = request.getParameter("videoLink");
    String productLink = request.getParameter("productLink");

    // Handle file upload
    Part filePart = request.getPart("file"); // Lấy phần file từ request

    if (filePart == null || filePart.getSize() == 0) {
        request.setAttribute("message", "No file selected.");
        request.getRequestDispatcher("EditNewsController").forward(request, response);
        return;
    }
    

    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp

    // Đường dẫn để lưu ảnh
    String uploadPath = getServletContext().getRealPath("/ImageNews");
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
        uploadDir.mkdirs();
    }

    String filePath = uploadPath + File.separator + fileName;
    try {
        filePart.write(filePath);
    } catch (IOException e) {
        request.setAttribute("message", "File upload failed.");
        request.getRequestDispatcher("EditNewsController").forward(request, response);
        return;
    }

    Date currentDate = new Date();
    String avatarP = fileName;

    // Tạo đối tượng News
 
    // Gọi hàm editNews và kiểm tra kết quả
    boolean testUpdate = dao.addNews(content, avatarP, title, author, videoLink, productLink);
    if (testUpdate) {
        request.setAttribute("message", "Add successful.");
    } else {
        request.setAttribute("message", "Add failed.");
    }

    // Chuyển hướng về trang EditNews.jsp
    request.getRequestDispatcher("/Views/Admin/AddNews.jsp").forward(request, response);    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
