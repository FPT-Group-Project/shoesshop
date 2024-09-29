package ControllersAdmin;

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

@WebServlet(name = "AddProductServlet", urlPatterns = {"/addProduct"})
@MultipartConfig
public class AddProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển tiếp đến trang JSP hiển thị form thêm sản phẩm
        request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // lấy giá trị từ form
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));

        // xử lý file upload
        Part filePart = request.getPart("avatarP"); // lấy file từ input form
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // lấy tên file

        // đường dẫn thư mục cho ảnh sản phẩm
        String uploadPath = getServletContext().getRealPath("/images");

        // tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // đường dẫn đầy đủ của file để lưu trên server
        String filePath = uploadPath + File.separator + fileName;

        // lưu file vào thư mục
        filePart.write(filePath);

        // lưu đường dẫn ảnh vào db 
        String avatarP = "images/" + fileName;

        // tạo đối tượng Product
        Product product = new Product(productName, description, quantity, price, brandId, avatarP);

        // gọi DAO để thêm product vào db
        ProductDAO productDAO = new ProductDAO();
        productDAO.insertProduct(product);

        // đặt thông báo thành công vào request
        request.setAttribute("successMessage", "Sản phẩm đã được thêm thành công!");

        // chuyển tiếp về trang addProduct.jsp
        request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
    }
}
