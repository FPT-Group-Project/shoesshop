/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Cart;
import Models.Order;
import Models.OrderDetail;
import Models.Product;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author thanh
 */

import java.sql.ResultSet;
import java.sql.Date;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO extends DBContext {

    // Thêm sản phẩm vào giỏ hàng
    public boolean addOrder(int accountId, List<Cart> carts, String address, String payment, double total) {
        String sql = "INSERT INTO [Order] (AccountID, Address, TotalPrice, OrderDate, StatusID, PaymentStatus) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, accountId);
            pre.setString(2, address);
            pre.setDouble(3, total);
            Date curr = new Date(new java.util.Date().getTime());
            pre.setDate(4, curr);
            pre.setInt(5, 1);
            pre.setString(6, payment);
            int rowsAffected = pre.executeUpdate();
            
                PreparedStatement p2 = connection.prepareStatement("SELECT TOP 1 [OrderID] FROM [Order] ORDER BY [OrderID] DESC");
                ResultSet odid = p2.executeQuery();
                int OrderID = 1;
                if(odid.next()) {
                    OrderID = odid.getInt("OrderID");
                }
            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i);
                PreparedStatement ps = connection.prepareStatement("INSERT INTO OrderDetail (OrderID, ProductID, StockID, Quantity, UnitPrice) VALUES (?, ?, ?, ?, ?)");
                ps.setInt(1, OrderID);
                ps.setInt(2, cart.getProductID());
                ps.setInt(3, cart.getStockID());
                ps.setInt(4, cart.getQuantity());
                ps.setDouble(5, cart.getQuantity() * cart.getProduct().getPrice());
                ps.executeUpdate();
                PreparedStatement ps2 = connection.prepareStatement("UPDATE ProductStock SET quantity = quantity - ? WHERE StockId = ?");
                ps2.setInt(1, cart.getQuantity());
                ps2.setInt(2, cart.getStockID());
                ps2.executeUpdate();
            }
            PreparedStatement od = connection.prepareStatement("DELETE FROM [Cart] WHERE AccountID = ?");
            od.setInt(1, accountId);
            od.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng được thêm
        } catch (SQLException e) {
            //System.out.println("Error adding to cart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        
    }
// Phương thức để lấy danh sách tất cả các đơn hàng
public List<Order> getAllOrders() {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT * FROM [Order]";
    
    try {
        PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();
        
        while (rs.next()) {
            Order order = new Order();
            order.setOrderID(rs.getInt("OrderID"));
            order.setAccountID(rs.getInt("AccountID"));
            order.setAddress(rs.getString("Address"));
            order.setTotalPrice(rs.getBigDecimal("TotalPrice"));
            order.setOrderDate(rs.getDate("OrderDate"));
            order.setArrivalDate(rs.getDate("ArrivalDate"));
            order.setStatusID(rs.getInt("StatusID"));
            order.setPaymentStatus(rs.getString("PaymentStatus"));
            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return orders;
}

// Phương thức để lấy chi tiết cho một đơn hàng cụ thể
public List<OrderDetail> getOrderDetails(int orderId) {
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
   

public List<Map<String, Object>> getAllOrdersWithCustomerNames() {
    List<Map<String, Object>> orders = new ArrayList<>();
    String sql = "SELECT o.*, a.fullname AS customerName FROM [Order] o JOIN Account a ON o.accountID = a.accountID";

    try (PreparedStatement pre = connection.prepareStatement(sql);
        ResultSet rs = pre.executeQuery();) {

        while (rs.next()) {
            Map<String, Object> orderData = new HashMap<>();
            orderData.put("orderID", rs.getInt("orderID"));
            orderData.put("accountID", rs.getInt("accountID"));
            orderData.put("customerName", rs.getString("customerName")); // Lấy tên khách hàng
            orderData.put("address", rs.getString("address"));
            orderData.put("totalPrice", rs.getBigDecimal("totalPrice"));
            orderData.put("orderDate", rs.getDate("orderDate"));
            orderData.put("arrivalDate", rs.getDate("arrivalDate"));
            orderData.put("statusID", rs.getInt("statusID"));
            orderData.put("paymentStatus", rs.getString("paymentStatus"));
            orders.add(orderData); // Thêm vào danh sách
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return orders;
}

public void updateOrderStatus(int orderID, int statusID) {
    String sql;

    // Nếu statusID là 4 (Delivered), hãy cập nhật ArrivalDate
    if (statusID == 4) {
        sql = "UPDATE [Order] SET StatusID = ?, ArrivalDate = ? WHERE OrderID = ?";
    } else {
        sql = "UPDATE [Order] SET StatusID = ? WHERE OrderID = ?";
    }

    try (PreparedStatement pre = connection.prepareStatement(sql)) {
        
        pre.setInt(1, statusID);
        
        if (statusID == 4) {
            pre.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Ghi lại thời điểm hiện tại
            pre.setInt(3, orderID);
        } else {
            pre.setInt(2, orderID);
        }
        
        pre.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}




}
