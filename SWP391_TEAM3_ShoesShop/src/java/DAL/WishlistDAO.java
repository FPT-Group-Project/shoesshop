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

public class WishlistDAO extends DBContext {

    // Thêm sản phẩm vào Wishlist
    public boolean addProductToWishlist(int accountId, int productId) throws SQLException {
        // Kiểm tra xem sản phẩm đã tồn tại trong Wishlist hay chưa
        String checkSql = "SELECT COUNT(*) FROM Wishlist WHERE AccountID = ? AND ProductID = ?";
        String insertSql = "INSERT INTO Wishlist (AccountID, ProductID, AddedDate) VALUES (?, ?, GETDATE())";

        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setInt(1, accountId);
            checkStmt.setInt(2, productId);

            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    // Nếu sản phẩm đã tồn tại trong Wishlist, trả về false để không thêm vào
                    System.out.println("Sản phẩm đã có trong Wishlist.");
                    return false;
                }
            }
        }

        // Thêm sản phẩm vào Wishlist nếu chưa tồn tại
        try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
            insertStmt.setInt(1, accountId);
            insertStmt.setInt(2, productId);
            int rowsAffected = insertStmt.executeUpdate();

            // Kiểm tra nếu thêm thành công (rowsAffected > 0)
            return rowsAffected > 0;
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
        String sql = "SELECT \n"
                + "    Wishlist.*, \n"
                + "    Product.AvatarP, \n"
                + "    Product.Price,\n"
                + "    Product.ProductName\n"
                + "FROM \n"
                + "    Wishlist\n"
                + "INNER JOIN \n"
                + "    Product ON Wishlist.ProductID = Product.ProductID\n"
                + "WHERE \n"
                + "    Wishlist.AccountID = ?;";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int productId = rs.getInt("ProductID");
                    Product product = productDAO.getProductById(productId); // Get Product from productId

                    Wishlist item = new Wishlist();
                    item.setWishlistId(rs.getInt("WishlistID"));
                    item.setAccountId(rs.getInt("AccountID"));
                    item.setProductId(product);
                    item.setAddedDate(rs.getTimestamp("AddedDate"));
                    item.setProductId(product);
                    // Set ProductName in the Wishlist object
                    item.setProductname(rs.getString("ProductName"));
                    item.setAvatarP(rs.getString("AvatarP"));
                    wishlist.add(item);
                }
            }
        }
        return wishlist;
    }

    public static void main(String[] args) {
      
    // Thay đổi các thông tin kết nối phù hợp với cấu hình của bạn

    try {
        // Tạo đối tượng WishlistDAO
        WishlistDAO wishlistDAO = new WishlistDAO();

        // Thay đổi các giá trị accountId và productId theo nhu cầu của bạn
        int accountId = 23; // Thay đổi ID người dùng nếu cần
        int productId = 4; // ID của sản phẩm cần xóa khỏi Wishlist

        // Thực hiện xóa sản phẩm khỏi Wishlist
        boolean isRemoved = wishlistDAO.removeProductFromWishlist(accountId, productId);

        // Kiểm tra kết quả xóa và hiển thị thông báo
        if (isRemoved) {
            System.out.println("Đã xóa sản phẩm có Product ID " + productId + " khỏi Wishlist.");
        } else {
            System.out.println("Không tìm thấy sản phẩm có Product ID " + productId + " trong Wishlist để xóa.");
        }

        // Lấy danh sách Wishlist để kiểm tra lại
        List<Wishlist> wishlist = wishlistDAO.getWishlistByAccountId(accountId);

        // In danh sách Wishlist ra màn hình sau khi xóa
        for (Wishlist item : wishlist) {
            System.out.println("Wishlist ID: " + item.getWishlistId());
            System.out.println("Account ID: " + item.getAccountId());
            System.out.println("Product ID: " + item.getProductId().getProductId());
            System.out.println("Product Name: " + item.getProductname());
            System.out.println("Avatar: " + item.getAvatarP());
            System.out.println("Added Date: " + item.getAddedDate());
            System.out.println("-------------------------------");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}