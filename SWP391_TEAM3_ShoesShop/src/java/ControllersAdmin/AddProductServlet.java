package ControllersAdmin;

import DAL.BrandDAO;
import DAL.DBContext;
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
import Models.ProductStock;
import DAL.ProductDAO;
import DAL.ProductStockDAO;
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
        // lấy giá trị từ form
        String productName = request.getParameter("productName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int brandId = Integer.parseInt(request.getParameter("brandId"));

        // Kiểm tra xem tên sản phẩm đã tồn tại trong cơ sở dữ liệu chưa
        ProductDAO productDAO = new ProductDAO();
        boolean productExists = productDAO.checkProductExists(productName);
        
        if (productExists) {
            request.setAttribute("errorMessage", "Product name already exists. Please choose a different name.");
            request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
            return; // Dừng lại nếu sản phẩm đã tồn tại
        }

        // xử lý file upload
        Part filePart = request.getPart("avatarP"); // lấy file từ input form
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "Please select a file to upload.");
            request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
            return;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
        String uploadPath = getServletContext().getRealPath("/ImageProductAvt");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String filePath = uploadPath + File.separator + fileName;
        try {
            filePart.write(filePath);
        } catch (IOException e) {
            request.setAttribute("errorMessage", "Error uploading file: " + e.getMessage());
            request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
            return;
        }

        String avatarP = fileName;
        Product product = new Product();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setBrandId(brandId);
        product.setAvatarP(avatarP);

        // Gọi DAO để thêm product vào db và lấy productID
        int newProductId = productDAO.addProduct(product); // Lấy ProductID mới
        if (newProductId > 0) {
            ProductStockDAO productStockDAO = new ProductStockDAO(new DBContext());
            for (int sizeID = 1; sizeID <= 14; sizeID++) {
                for (int colorID = 1; colorID <= 18; colorID++) {
                    ProductStock productStock = new ProductStock();
                    productStock.setProductID(newProductId);
                    productStock.setSizeID(sizeID);
                    productStock.setColorID(colorID);
                    productStock.setQuantity(0);
                    productStockDAO.insertProductStock(productStock);
                }
            }
            request.setAttribute("successMessage", "Product added successfully!");
        } else {
            request.setAttribute("errorMessage", "Failed to add product.");
        }

        request.getRequestDispatcher("/Views/Admin/addProduct.jsp").forward(request, response);
    }
}
