package ControllersAdmin;

import DAL.ProductStockDAO;
import Models.ProductStock;
import DAL.DBContext; // Import DBContext
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "UpdateStockServlet", urlPatterns = {"/updateStock"})
public class UpdateStockServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        int sizeID = Integer.parseInt(request.getParameter("sizeID"));
        int colorID = Integer.parseInt(request.getParameter("colorID"));
        String quantityChange = request.getParameter("quantityChange"); // Nhận loại thay đổi
        int quantityAmount = Integer.parseInt(request.getParameter("quantityAmount")); // Số lượng nhập vào

        // Lấy số lượng hiện tại từ cơ sở dữ liệu
        ProductStockDAO productStockDAO = new ProductStockDAO(new DBContext());
        int currentQuantity = productStockDAO.getQuantityByProductSizeColor(productID, sizeID, colorID);

        int newQuantity;
        if ("add".equals(quantityChange)) {
            newQuantity = currentQuantity + quantityAmount; // Thêm số lượng
        } else {
            newQuantity = Math.max(0, currentQuantity - quantityAmount); // Giảm số lượng, không cho phép âm
        }

        // Cập nhật số lượng trong ProductStock
        ProductStock productStock = new ProductStock();
        productStock.setProductID(productID);
        productStock.setSizeID(sizeID);
        productStock.setColorID(colorID);
        productStock.setQuantity(newQuantity);

        boolean success = productStockDAO.updateProductStock(productStock);

        // Chuyển hướng hoặc thông báo thành công
        if (success) {
            response.sendRedirect("manageStock?productID=" + productID);
        } else {
            response.sendRedirect("manageStock?productID=" + productID);
        }
    }
}
