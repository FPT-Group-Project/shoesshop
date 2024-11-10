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
        this.dbContext = dbContext; 
    }

  
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
                stock.setProductID(productId); 
                stock.setSizeID(rs.getInt("SizeID")); 
                stock.setColorID(rs.getInt("ColorID")); 
                stock.setQuantity(rs.getInt("Quantity")); 
                productStocks.add(stock); // Thêm vào danh sách
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productStocks; 
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
public int getProductStockId(int productID, int sizeID, int colorID) {
    int stockId = 0;
    String sql = "SELECT StockId FROM ProductStock WHERE ProductID = ? AND SizeID = ? AND ColorID = ?";

    try {
        PreparedStatement ps = dbContext.connection.prepareStatement(sql);
        ps.setInt(1, productID);
        ps.setInt(2, sizeID);
        ps.setInt(3, colorID);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            stockId = rs.getInt("StockId");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return stockId;
}
public boolean updateSeriStatus(int stockId, int quantityToUpdate) {
    String selectSql = "SELECT seri FROM Seri WHERE stockId = ? AND status = 'available' LIMIT ?";
    String updateSql = "UPDATE Seri SET status = 'deleted' WHERE seri IN (SELECT seri FROM Seri WHERE stockId = ? AND status = 'available' LIMIT ?)";

    try (PreparedStatement selectPs = dbContext.connection.prepareStatement(selectSql);
         PreparedStatement updatePs = dbContext.connection.prepareStatement(updateSql)) {
         
        // Lấy danh sách các seri với stockId tương ứng
        selectPs.setInt(1, stockId);
        selectPs.setInt(2, quantityToUpdate); // Giới hạn số lượng seri được chọn

        ResultSet rs = selectPs.executeQuery();
        List<Integer> seriList = new ArrayList<>();

        while (rs.next()) {
            seriList.add(rs.getInt("seri"));
        }

        // Chỉ cập nhật trạng thái nếu có seri tương ứng
        if (!seriList.isEmpty()) {
            updatePs.setInt(1, stockId);
            updatePs.setInt(2, quantityToUpdate);
            return updatePs.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Trả về false nếu không có seri nào để cập nhật
}

    public boolean updateSeriStatusToDeleted(int stockId, int quantityToDelete) {
        String sql = "UPDATE Seri SET status = 'deleted' WHERE stockId = ? AND status = 'available' ORDER BY seri OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement ps = dbContext.connection.prepareStatement(sql)) {
            ps.setInt(1, stockId);
            ps.setInt(2, quantityToDelete);
            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi
        }
    }
    
    public ProductStock getProductStockById(int stockID) {
        ProductStock productStock = null;
        String sql = "SELECT * FROM ProductStock WHERE StockID = ?";

        try {
            PreparedStatement pre = dbContext.connection.prepareStatement(sql);
            pre.setInt(1, stockID);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                productStock = new ProductStock();
                productStock.setStockId(rs.getInt("StockID"));
                productStock.setProductID(rs.getInt("ProductID"));
                productStock.setSizeID(rs.getInt("SizeID"));    // SizeID là kiểu int
                productStock.setColorID(rs.getInt("ColorID"));  // ColorID là kiểu int
                productStock.setQuantity(rs.getInt("Quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productStock;
    }


}
