package DAL;

import Models.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO extends DBContext {

    // Phương thức để thêm chi tiết đơn hàng
    public boolean addOrderDetail(int orderId, int productId, int stockId, int quantity, double unitPrice) {
        String sql = "INSERT INTO OrderDetail (OrderID, ProductID, StockID, Quantity, UnitPrice) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, orderId);
            pre.setInt(2, productId);
            pre.setInt(3, stockId);
            pre.setInt(4, quantity);
            pre.setDouble(5, unitPrice);
            int rowsAffected = pre.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng được thêm
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Phương thức để lấy chi tiết cho một đơn hàng cụ thể
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetail WHERE OrderID = ?";
        
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, orderId);
            ResultSet rs = pre.executeQuery();
            
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderDetailID(rs.getInt("OrderDetailID"));
                detail.setOrderID(rs.getInt("OrderID"));
                detail.setProductID(rs.getInt("ProductID"));
                detail.setStockID(rs.getInt("StockID"));
                detail.setQuantity(rs.getInt("Quantity"));
                detail.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                orderDetails.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orderDetails;
    }

    
}
