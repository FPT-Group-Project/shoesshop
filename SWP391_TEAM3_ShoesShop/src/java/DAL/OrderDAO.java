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
        String sql = "INSERT INTO [Order] (AccountID, Address, TotalPrice, OrderDate, StatusID, PaymentStatus, OrderID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement p = connection.prepareStatement("SELECT TOP 1 [OrderID] FROM [Order] ORDER BY [OrderID] DESC");
            ResultSet oid = p.executeQuery();
            int OrderID = 1;
            if(oid.next()) {
                OrderID = oid.getInt("OrderID") + 1;
            }
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, accountId);
            pre.setString(2, address);
            pre.setDouble(3, total);
            Date curr = new Date(new java.util.Date().getTime());
            pre.setDate(4, curr);
            pre.setInt(5, 1);
            pre.setString(6, payment);
            pre.setInt(7, OrderID);
            int rowsAffected = pre.executeUpdate();
            
                PreparedStatement p2 = connection.prepareStatement("SELECT TOP 1 [OrderDetailID] FROM [OrderDetail] ORDER BY [OrderDetailID] DESC");
                ResultSet odid = p2.executeQuery();
                int odId = 1;
                if(odid.next()) {
                    odId = odid.getInt("OrderDetailID") + 1;
                }
            for (int i = 0; i < carts.size(); i++) {
                Cart cart = carts.get(i);
                PreparedStatement ps = connection.prepareStatement("INSERT INTO OrderDetail (OrderID, ProductID, ColorID, SizeID, Quantity, UnitPrice, OrderDetailID) VALUES (?, ?, ?, ?, ?, ?, ?)");
                ps.setInt(1, OrderID);
                ps.setInt(2, cart.getProductID());
                ps.setInt(3, cart.getColorID());
                ps.setInt(4, cart.getSizeID());
                ps.setInt(5, cart.getQuantity());
                ps.setDouble(6, cart.getQuantity() * cart.getProduct().getPrice());
                ps.setInt(7, odId);
                ps.executeUpdate();
                odId++;
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
