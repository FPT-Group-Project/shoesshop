/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Cart;
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
import java.util.List;

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

   
 
}
