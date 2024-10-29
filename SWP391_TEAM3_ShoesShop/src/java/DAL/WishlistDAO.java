/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

/**
 *
 * @author thanh
 */

import Models.Product;
import Models.Wishlist;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO extends   DBContext{
  

   
    // Thêm sản phẩm vào Wishlist
    public boolean addProductToWishlist(int accountId, int productId) throws SQLException {
        String sql = "INSERT INTO Wishlist (AccountID, ProductID, AddedDate) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setInt(2, productId);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            return stmt.executeUpdate() > 0;
        }
    }

    // Xóa sản phẩm khỏi Wishlist
    public boolean removeProductFromWishlist(int accountId, int productId) throws SQLException {
        String sql = "DELETE FROM Wishlist WHERE AccountID = ? AND ProductID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setInt(2, productId);
            return stmt.executeUpdate() > 0;
        }
    }
ProductDAO productDAO = new ProductDAO();
    // Lấy danh sách sản phẩm trong Wishlist của người dùng
    public List<Wishlist> getWishlistByAccountId(int accountId) throws SQLException {
        List<Wishlist> wishlist = new ArrayList<>();
        String sql = "SELECT * FROM Wishlist WHERE AccountID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    Product product = productDAO.getProductById(productId); // Lấy Product từ productId
                    Wishlist item = new Wishlist();
                    item.setWishlistId(rs.getInt("WishlistID"));
                    item.setAccountId(rs.getInt("AccountID"));
                    item.setProductId(product);
                   
                    item.setAddedDate(rs.getTimestamp("AddedDate"));
                    wishlist.add(item);
                }
            }
        }
        return wishlist;
    }
     public static void main(String[] args) {
        // Thay đổi các thông tin kết nối phù hợp với cấu hình của bạn
       
        // Kết nối đến cơ sở dữ liệu
        try  {
            // Tạo đối tượng WishlistDAO
            WishlistDAO wishlistDAO = new WishlistDAO();

            // Thay đổi các giá trị accountId và productId theo nhu cầu của bạn
            int accountId = 23; // ID của tài khoản
            int productId = 1; // ID của sản phẩm

            // Gọi hàm addProductToWishlist
            boolean result = wishlistDAO.addProductToWishlist(accountId, productId);

            if (result) {
                System.out.println("Sản phẩm đã được thêm vào Wishlist thành công.");
            } else {
                System.out.println("Không thể thêm sản phẩm vào Wishlist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
