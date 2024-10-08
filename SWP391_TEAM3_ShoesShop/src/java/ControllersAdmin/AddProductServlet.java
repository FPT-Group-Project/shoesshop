package ControllersAdmin;

import DAL.BrandDAO;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import Models.Product;
import DAL.ProductDAO;
import Models.Account;
import Models.Brand;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/addProduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // Kiểm tra quyền truy cập
//    HttpSession session = request.getSession(false);
//    if (session == null || session.getAttribute("account") == null) {
//        response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
//        return;
//    }
//
//    Account account = (Account) session.getAttribute("account");
//    int roleId = Integer.parseInt(account.getRoleId());
//    
//    // Kiểm tra xem người dùng có phải là admin hoặc staff không
//    if (roleId != 1 && roleId != 2) {
//        response.sendRedirect("accessDenied.jsp"); // Chuyển hướng đến trang từ chối quyền truy cập
//        return;
//    }
        BrandDAO brandDAO = new BrandDAO();
        List<Brand> brandList = (List<Brand>) brandDAO.getAllBrands();

        // đặt danh sách brand vào request
        request.setAttribute("brandList", brandList);

        // chuyển tiếp đến trang addProduct.jsp
        request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // kiểm tra role
//    HttpSession session = request.getSession(false);
//    if (session == null || session.getAttribute("account") == null) {
//        response.sendRedirect("login.jsp"); // Chuyển hướng đến trang đăng nhập nếu chưa đăng nhập
//        return;
//    }
//
//    Account account = (Account) session.getAttribute("account");
//    int roleId = Integer.parseInt(account.getRoleId());
//    
//    // Kiểm tra xem người dùng có phải là admin hoặc staff không
//    if (roleId != 1 && roleId != 2) {
//        response.sendRedirect("accessDenied.jsp"); // Chuyển hướng đến trang từ chối quyền truy cập
//        return;
//    }

        // lấy giá trị từ form
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));

        // xử lý file upload
        Part filePart = request.getPart("avatarP"); // lấy file từ input form

        // Kiểm tra file có được chọn không
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "Please select a file to upload.");
            request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
            return;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // lấy tên file

        // đường dẫn thư mục cho ảnh sản phẩm
        String uploadPath = getServletContext().getRealPath("/ImageProductAvt");

        // tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // đường dẫn đầy đủ của file để lưu trên server
        String filePath = uploadPath + File.separator + fileName;

        // lưu file vào thư mục
        try {
            filePart.write(filePath);
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
            return;
        }

        // lưu đường dẫn ảnh vào db 
        String avatarP = "ImageProductAvt/" + fileName;

        // tạo đối tượng Product
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setBrandId(brandId);
        product.setAvatarP(avatarP);

        // gọi DAO để thêm product vào db
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProduct(product);

        // đặt thông báo thành công vào request
        request.setAttribute("successMessage", "Product added successfully!");

        // chuyển tiếp về trang addProduct.jsp
        request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
    }
}
