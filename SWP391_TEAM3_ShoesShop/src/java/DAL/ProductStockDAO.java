package DAL;

import Models.ProductStock;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class ProductStockDAO {
    private final DBContext dbContext;

    public ProductStockDAO(DBContext dbContext) {
        this.dbContext = dbContext; // Sử dụng DBContext có sẵn
    }

    // Cập nhật số lượng trong bảng ProductStock
    public boolean updateProductStock(ProductStock productStock) {
        String sql = "UPDATE ProductStock SET quantity = ? WHERE ProductID = ? AND SizeID = ? AND ColorID = ?";
        try {
            PreparedStatement ps = dbContext.connection.prepareStatement(sql);
            ps.setInt(1, productStock.getQuantity());
            ps.setInt(2, productStock.getProductID());
            ps.setInt(3, productStock.getSizeID());
            ps.setInt(4, productStock.getColorID());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean insertProductStock(ProductStock productStock) {
    String sql = "INSERT INTO ProductStock(ProductID, SizeID, ColorID, quantity) VALUES (?, ?, ?, ?)";
    try (
         PreparedStatement ps = dbContext.connection.prepareStatement(sql)) {
        ps.setInt(1, productStock.getProductID());
        ps.setInt(2, productStock.getSizeID());
        ps.setInt(3, productStock.getColorID());
        ps.setInt(4, productStock.getQuantity());
        return ps.executeUpdate() > 0; // Trả về true nếu chèn thành công
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Trả về false nếu có lỗi
    }
}
        public List<ProductStock> getProductStocksByProductId(int productId) {
        List<ProductStock> productStocks = new ArrayList<>();
        String sql = "SELECT SizeID, ColorID, Quantity FROM ProductStock WHERE ProductID = ?";
        try {
            PreparedStatement ps = dbContext.connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductStock stock = new ProductStock();
                stock.setProductID(productId); // Set ProductID
                stock.setSizeID(rs.getInt("SizeID")); // Set SizeID từ kết quả truy vấn
                stock.setColorID(rs.getInt("ColorID")); // Set ColorID từ kết quả truy vấn
                stock.setQuantity(rs.getInt("Quantity")); // Set Quantity từ kết quả truy vấn
                productStocks.add(stock); // Thêm vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productStocks; // Trả về danh sách ProductStock
    }

public int getQuantityByProductSizeColor(int productID, int sizeID, int colorID) {
        int quantity = 0;
        String sql = "SELECT quantity FROM ProductStock WHERE ProductID = ? AND SizeID = ? AND ColorID = ?";

        try {
            PreparedStatement ps = dbContext.connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setInt(2, sizeID);
            ps.setInt(3, colorID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantity;
    }
}
