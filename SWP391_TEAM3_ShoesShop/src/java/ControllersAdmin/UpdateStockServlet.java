package ControllersAdmin;

import DAL.ProductStockDAO;
import DAL.SeriDAO; // Thêm import cho SeriDAO
import Models.ProductStock;
import Models.Seri; // Thêm import cho Seri
import DAL.DBContext; 
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

        boolean stockUpdateSuccess = productStockDAO.updateProductStock(productStock);

        // Nếu cập nhật thành công, xử lý thêm seri hoặc cập nhật trạng thái
        if (stockUpdateSuccess) {
            SeriDAO seriDAO = new SeriDAO(new DBContext());
            int stockId = productStockDAO.getProductStockId(productID, sizeID, colorID); // Lấy stockId từ ProductStock

            if ("add".equals(quantityChange)) {
                // Thêm seri mới cho số lượng đã thêm
                for (int i = 0; i < quantityAmount; i++) {
                    Seri seri = new Seri();
                    seri.setStockId(stockId);
                    seri.setStatus("available"); // Trạng thái mới
                    seriDAO.insertSeri(seri); // Thêm seri vào database
                }
            } else if ("subtract".equals(quantityChange)) {
                boolean statusUpdateSuccess = seriDAO.updateSeriStatusToDeleted(stockId, quantityAmount);
                if (!statusUpdateSuccess) {
                    // Xử lý lỗi nếu không cập nhật được trạng thái
                    response.sendRedirect("manageStock?productID=" + productID + "&error=updateStatusFailed");
                    return;
                }
            }
            response.sendRedirect("manageStock?productID=" + productID); // Chuyển hướng thành công
        } else {
            response.sendRedirect("manageStock?productID=" + productID + "&error=stockUpdateFailed"); // Chuyển hướng nếu có lỗi
        }
    }
}