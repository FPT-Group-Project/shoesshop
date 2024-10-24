package ControllersAdmin;

import DAL.BrandDAO;
import DAL.ProductDAO;
import DAL.SizeDAO;
import DAL.ColorDAO;
import DAL.ProductStockDAO; // Thêm DAO cho ProductStock
import Models.Product;
import Models.Brand;
import Models.Size;
import Models.Color;
import Models.ProductStock;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "StockManageServlet", urlPatterns = {"/manageStock"})
public class StockManageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productID = request.getParameter("productID");
        int productId = Integer.parseInt(productID);

        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);

        // Lấy danh sách các thương hiệu từ BrandDAO
        BrandDAO brandDAO = new BrandDAO();
        List<Brand> brandList = brandDAO.getAllBrands();

        // Lấy danh sách kích thước và màu sắc
        SizeDAO sizeDAO = new SizeDAO();
        ColorDAO colorDAO = new ColorDAO();
        List<Size> sizeList = sizeDAO.getAllSizes();
        List<Color> colorList = colorDAO.getAllColors();

        // Ánh xạ kích thước và màu sắc theo ID
        Map<Integer, Size> sizeMap = sizeList.stream().collect(Collectors.toMap(Size::getSizeId, size -> size));
        Map<Integer, Color> colorMap = colorList.stream().collect(Collectors.toMap(Color::getColorId, color -> color));

// Truyền qua JSP
        request.setAttribute("sizeMap", sizeMap);
        request.setAttribute("colorMap", colorMap);

        // Lấy thông tin tồn kho sản phẩm
        ProductStockDAO productStockDAO = new ProductStockDAO(brandDAO);
        List<ProductStock> productStockList = productStockDAO.getProductStocksByProductId(productId);

        // Ghi thông tin sản phẩm, danh sách thương hiệu, kích thước, màu sắc và tồn kho vào request
        request.setAttribute("product", product);
        request.setAttribute("brandList", brandList);
        request.setAttribute("sizeList", sizeList);
        request.setAttribute("colorList", colorList);
        request.setAttribute("productStockList", productStockList);

        // Chuyển tiếp tới trang JSP để hiển thị
        request.getRequestDispatcher("Views/Admin/manageStock.jsp").forward(request, response);
    }
}
