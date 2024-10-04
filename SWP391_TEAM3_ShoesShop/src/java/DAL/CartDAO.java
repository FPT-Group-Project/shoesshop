/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Cart;
import Models.Product;
import Models.Stock;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author thanh
 */

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class CartDAO extends DBContext {

    // Thêm sản phẩm vào giỏ hàng
    public boolean addToCart(Cart cart, int ColorID, int SizeID) {
        String sql = "INSERT INTO Cart (ProductID, AccountID, StockID, Quantity) VALUES (?, ?, (SELECT TOP 1 StockId FROM ProductStock WHERE ProductID = ? AND SizeID = ? AND ColorID = ?), ?)";
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Cart WHERE ProductID = ? AND AccountID = ? AND StockID = (SELECT TOP 1 StockId FROM ProductStock WHERE ProductID = ? AND SizeID = ? AND ColorID = ?)");
			ps.setInt(1, cart.getProductID());
			ps.setInt(2, cart.getAccountID());
            ps.setInt(3, cart.getProductID());
            ps.setObject(4, SizeID, java.sql.Types.INTEGER);
            ps.setObject(5, ColorID, java.sql.Types.INTEGER);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				PreparedStatement pre = connection.prepareStatement("UPDATE Cart SET Quantity = Quantity + ? WHERE CartID = ?");
				pre.setInt(1, cart.getQuantity());
				pre.setInt(2, rs.getInt("CartID"));
				int rowsAffected = pre.executeUpdate();
				return rowsAffected > 0; // Trả về true nếu có dòng được thêm
			} else {
				PreparedStatement pre = connection.prepareStatement(sql);
				pre.setInt(1, cart.getProductID());
				pre.setInt(2, cart.getAccountID());
				pre.setInt(3, cart.getProductID());
				pre.setObject(4, SizeID, java.sql.Types.INTEGER);
				pre.setObject(5, ColorID, java.sql.Types.INTEGER);
				pre.setInt(6, cart.getQuantity());
				int rowsAffected = pre.executeUpdate();
				return rowsAffected > 0; // Trả về true nếu có dòng được thêm
			}
        } catch (SQLException e) {
            System.out.println("Error adding to cart: " + e.getMessage());
            return false;
        }
    }

    // Lấy danh sách các mục trong giỏ hàng của người dùng
    public List<Cart> getCartItemsByAccountId(int accountId) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT Cart.*, Product.*, ps.quantity as StockQuan, ps.SizeID, ps.ColorID, pc.Color, psz.Size FROM Cart join Product on Cart.ProductID = Product.ProductID join ProductStock ps on Cart.StockID = ps.StockId join Product_Color pc on pc.ColorID = ps.ColorID join Product_Size psz on psz.SizeID = ps.SizeID WHERE Cart.AccountID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, accountId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int cartId = rs.getInt("CartID");
                int productId = rs.getInt("ProductID");
                int StockID = rs.getInt("StockID");
                int quantity = rs.getInt("Quantity");

                Cart cartItem = new Cart(cartId, productId, accountId, StockID, quantity);
                cartItem.setProduct(new Product(productId, rs.getString("ProductName"), rs.getString("Description"), rs.getDouble("Price"), rs.getInt("BrandID"), rs.getString("AvatarP")));
				Stock s = new Stock(StockID, productId, rs.getInt("SizeID"), rs.getInt("ColorID"), rs.getInt("StockQuan"));
				s.setSize(rs.getInt("Size"));
				s.setColor(rs.getString("Color"));
                cartItem.setStock(s);
                cartList.add(cartItem);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching cart items: " + e.getMessage());
            return new ArrayList<>();
        }
        return cartList;
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeFromCart(int cartId) {
        String sql = "DELETE FROM Cart WHERE CartID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, cartId);
            int rowsAffected = pre.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng bị xóa
        } catch (SQLException e) {
            System.out.println("Error removing from cart: " + e.getMessage());
            return false;
        }
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public boolean updateCartQuantity(int cartId, int newQuantity) {
        String sql = "UPDATE Cart SET Quantity = ? WHERE CartID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, newQuantity);
            pre.setInt(2, cartId);
            int rowsAffected = pre.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có dòng được cập nhật
        } catch (SQLException e) {
            System.out.println("Error updating cart quantity: " + e.getMessage());
            return false;
        }
    }

    public Cart getCartItemByProductIdAndAccountId(int productId, Integer accountId) {
          String sql = "SELECT * FROM Cart WHERE ProductID = ? AND AccountID = ?";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, productId);
            pre.setInt(2, accountId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                int cartId = rs.getInt("CartID");
                int StockID = rs.getInt("StockID");
                int quantity = rs.getInt("Quantity");

                return new Cart(cartId, productId, accountId, StockID, quantity);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching cart item: " + e.getMessage());
        }
        return null; // Nếu không tìm thấy sản phẩm trong giỏ hàng
    }
      public int countItemsByAccountId(int accountId) {
        int itemCount = 0;
        String sql = "SELECT COUNT(ProductID) AS itemCount FROM Cart WHERE AccountID = ?";
        
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, accountId);  // Gán AccountID từ tham số
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                itemCount = rs.getInt("itemCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemCount;
    }
 public static void main(String[] args) {
        // Khởi tạo đối tượng CartDAO
        CartDAO cartDAO = new CartDAO();
        
        // Tạo một sản phẩm để thêm vào giỏ hàng
        Cart cartItem = new Cart();
        
        cartItem.setProductID(1); // Thay đổi ProductID theo sản phẩm có sẵn trong cơ sở dữ liệu
        cartItem.setAccountID(3); // Thay đổi AccountID theo tài khoản của người dùng
        int ColorID = 13;   // Thay đổi ColorID nếu cần
        int SizeID = 2;    // Thay đổi SizeID nếu cần
        cartItem.setQuantity(2);   // Số lượng sản phẩm muốn thêm vào giỏ hàng
        
        // Thực hiện thêm sản phẩm vào giỏ hàng
        boolean result = cartDAO.addToCart(cartItem, ColorID, SizeID);
        
        // Kiểm tra kết quả
        /*
        if (result) {
            System.out.println("Sản phẩm đã được thêm vào giỏ hàng thành công!");
        } else {
            System.out.println("Lỗi khi thêm sản phẩm vào giỏ hàng.");
        }*/
    }
    // Phương thức thêm sản phẩm vào giỏ hàng

   
 
}
