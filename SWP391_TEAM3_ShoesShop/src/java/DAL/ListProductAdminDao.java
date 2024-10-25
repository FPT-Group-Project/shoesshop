package DAL;

import Models.ProductAdmin;
import Models.Brand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListProductAdminDao extends DBContext {
    // xóa producct qua id
public boolean deleteProductById(int productID) {
    // Các câu lệnh SQL để xóa bản ghi trong các bảng liên quan
    String deleteFromProductImage = "DELETE FROM [dbo].[Product_Image] WHERE [ProductID] = ?";
    String deleteFromProductStock = "DELETE FROM [dbo].[ProductStock] WHERE [ProductID] = ?";
    String deleteFromOrderDetail = "DELETE FROM [dbo].[OrderDetail] WHERE [ProductID] = ?";
    String deleteFromFeedback = "DELETE FROM [dbo].[Feedback] WHERE [ProductID] = ?";
    String deleteFromCart = "DELETE FROM [dbo].[Cart] WHERE [ProductID] = ?";
    String deleteFromProduct = "DELETE FROM [dbo].[Product] WHERE [ProductID] = ?";

    try {
        connection.setAutoCommit(false); // Bắt đầu giao dịch

        // Bước 1: Xóa các bản ghi trong bảng Product_Image trước
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromProductImage)) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
        }

        // Bước 2: Xóa các bản ghi trong bảng ProductStock
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromProductStock)) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
        }

        // Bước 3: Xóa các bản ghi trong bảng OrderDetail
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromOrderDetail)) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
        }

        // Bước 4: Xóa các bản ghi trong bảng Feedback
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromFeedback)) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
        }

       

        // Bước 6: Xóa các bản ghi trong bảng Cart
        try (PreparedStatement stmt = connection.prepareStatement(deleteFromCart)) {
            stmt.setInt(1, productID);
            stmt.executeUpdate();
        }

       

          try (PreparedStatement stmt = connection.prepareStatement(deleteFromProduct)) {
            stmt.setInt(1, productID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product has been successfully deleted.");
                connection.commit(); // Xác nhận giao dịch
                return true;
            } else {
                System.out.println("No product found with the given ID.");
            }
        }

        connection.commit(); // Xác nhận giao dịch
    } catch (SQLException e) {
        e.printStackTrace(); // Xử lý lỗi
        try {
            connection.rollback(); // Hoàn tác giao dịch nếu có lỗi
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            connection.setAutoCommit(true); // Đặt lại chế độ tự động cam kết
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    return false; // Trả về false nếu không xóa thành công
}

public void deleteBrandById(int brandID) {
    String sql = "DELETE FROM [dbo].[Brand] WHERE [BrandID] = ?";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, brandID);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Brand has been successfully deleted.");
        } else {
            System.out.println("No brand found with the given ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void deleteAccountById(int accountID) {
    String sql = "DELETE FROM [dbo].[Account] WHERE [AccountID] = ?";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setInt(1, accountID);

        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Account has been successfully deleted.");
        } else {
            System.out.println("No account found with the given ID.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


  public int getQuantity(int productId) {
        int totalQuantity = 0;

        String query = "SELECT SUM(quantity) AS total_quantity FROM ProductStock WHERE ProductID = ?";
        
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalQuantity = rs.getInt("total_quantity"); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalQuantity; 
    }
    // Phương thức lấy danh sách sản phẩm dành cho Admin
public List<ProductAdmin> getProductListForAdmin(int page, Integer brandID) {
    List<ProductAdmin> productList = new ArrayList<>();
    int itemsPerPage = 5; 
    int offset = (page - 1) * itemsPerPage; 

    String sql;
    if (brandID != null) {
        sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.BrandID, p.AvatarP, c.BrandName " +
              "FROM [dbo].[Product] p " +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID " +
              "WHERE p.BrandID = ? " +
              "ORDER BY p.ProductID desc " +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    } else {
        sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.BrandID, p.AvatarP, c.BrandName " +
              "FROM [dbo].[Product] p " +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID " +
              "ORDER BY p.ProductID desc " +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    }

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        if (brandID != null) {
            stmt.setInt(1, brandID);  
            stmt.setInt(2, offset);   
            stmt.setInt(3, itemsPerPage); 
        } else {
            stmt.setInt(1, offset);   
            stmt.setInt(2, itemsPerPage);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Brand brand = new Brand();
            brand.setBrandID(rs.getInt("BrandID"));
            brand.setBrandName(rs.getString("BrandName"));

            ProductAdmin product = new ProductAdmin(
                rs.getInt("ProductID"),            
                rs.getString("ProductName"),       
                rs.getString("Description"),       
                rs.getDouble("Price"),             
                brand,                           
                rs.getString("AvatarP"),          
                    getQuantity(rs.getInt("ProductID"))        
            );

            productList.add(product);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return productList;
}


    public List< Brand> getListBrand() {
        List<Brand> listCate = new ArrayList<>();
       String sql = "SELECT * FROM Brand ";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
           
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Brand cate = new Brand(
                        rs.getInt("BrandID"),
                        rs.getString("BrandName"));
                listCate.add(cate);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
        }
        return listCate;
    }
    
public List<ProductAdmin> getProductListForAdmin(int page, Integer brandID, String keyWord) {
    List<ProductAdmin> productList = new ArrayList<>();
    int itemsPerPage = 5; 
    int offset = (page - 1) * itemsPerPage;

    String sql;
    if (brandID != null) {
        // Tìm kiếm cả theo BrandID và keyWord (trong cả ProductName và Description)
        sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.BrandID, p.AvatarP, c.BrandName " +
              "FROM [dbo].[Product] p " +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID " +
              "WHERE p.BrandID = ? " +
              "AND (p.ProductName LIKE ? OR p.Description LIKE ?) " + 
              "ORDER BY p.ProductID desc " +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    } else {
        sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.BrandID, p.AvatarP, c.BrandName " +
              "FROM [dbo].[Product] p " +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID " +
              "WHERE (p.ProductName LIKE ? OR p.Description LIKE ?) " +
              "ORDER BY p.ProductID desc " +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    }

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        int parameterIndex = 1;

        if (brandID != null) {
            stmt.setInt(parameterIndex++, brandID);
        }

        stmt.setString(parameterIndex++, "%" + keyWord + "%");  
        stmt.setString(parameterIndex++, "%" + keyWord + "%");

       
        stmt.setInt(parameterIndex++, offset);
        stmt.setInt(parameterIndex, itemsPerPage);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandID(rs.getInt("BrandID"));
                brand.setBrandName(rs.getString("BrandName"));

                ProductAdmin product = new ProductAdmin(
                        rs.getInt("ProductID"),
                        rs.getString("ProductName"),
                        rs.getString("Description"),
                        rs.getDouble("Price"),
                        brand,
                        rs.getString("AvatarP"),
                        getQuantity(rs.getInt("ProductID"))  
                );

                productList.add(product);  // 
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  
    }

    return productList;
}

public int countProductListForAdmin(Integer brandID, String keyWord) {
    int totalProducts = 0; 


    String sql = "SELECT COUNT(*)\n" +
                 "FROM [dbo].[Product] p\n" +
                 "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID\n" +
                 "WHERE p.ProductName LIKE ?\n"; 

    if (brandID != null) {
        sql += "AND p.BrandID = ?\n";
    }

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, "%" + keyWord + "%"); 

        if (brandID != null) {
            stmt.setInt(2, brandID); 
        }

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalProducts = rs.getInt(1); 
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalProducts; 
}


   public int countProductListForAdmin(Integer brandID) {
    int totalProducts = 0; 
    String sql;
    if (brandID != null) {
        sql = "SELECT COUNT(*)\n" +
              "FROM [dbo].[Product] p\n" +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID\n" +
              "WHERE p.BrandID = ?";
    } else {
        sql = "SELECT COUNT(*)\n" +
              "FROM [dbo].[Product] p\n" +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID";
    }

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        if (brandID != null) {
            stmt.setInt(1, brandID); 
        }

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            totalProducts = rs.getInt(1); 
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return totalProducts;
}

public List<ProductAdmin> getProductListForAdmin1(int page, Integer brandID) {
    List<ProductAdmin> productList = new ArrayList<>();
    int itemsPerPage = 5;
    int offset = (page - 1) * itemsPerPage;

    String sql;
    if (brandID != null) {
        sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.AvatarP, b.BrandID, b.BrandName, ps.Quantity\n" +
              "FROM [dbo].[Product] p\n" +
              "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n" +
              "JOIN [dbo].[ProductStock] ps ON p.ProductID = ps.ProductID\n" +
              "WHERE p.BrandID = ?\n" +
              "ORDER BY p.ProductID\n" +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    } else {
        sql = "SELECT p.ProductID, p.ProductName, p.Description, p.Price, p.AvatarP, b.BrandID, b.BrandName, ps.Quantity\n" +
              "FROM [dbo].[Product] p\n" +
              "JOIN [dbo].[Brand] b ON p.BrandID = b.BrandID\n" +
              "JOIN [dbo].[ProductStock] ps ON p.ProductID = ps.ProductID\n" +
              "ORDER BY p.ProductID\n" +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    }

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        if (brandID != null) {
            stmt.setInt(1, brandID); 
            stmt.setInt(2, offset);  
            stmt.setInt(3, itemsPerPage); 
        } else {
            stmt.setInt(1, offset);  
            stmt.setInt(2, itemsPerPage); 
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Brand brand = new Brand();
            brand.setBrandID(rs.getInt("BrandID"));
            brand.setBrandName(rs.getString("BrandName"));

            ProductAdmin product = new ProductAdmin(
                    rs.getInt("ProductID"),
                    rs.getString("ProductName"),
                    rs.getString("Description"),
                    rs.getDouble("Price"),
                    brand,
                    rs.getString("AvatarP"),
                    rs.getInt("Quantity") 
            );
            productList.add(product);
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return productList;
}


      public static void main(String[] args) {
        // Khởi tạo đối tượng DAO để quản lý sản phẩm
        ListProductAdminDao productAdminDao = new ListProductAdminDao();

        // Xác định ID sản phẩm cần xóa
        int productIDToDelete = 3; // Thay đổi ID theo sản phẩm bạn muốn xóa

        try {
            // Gọi phương thức deleteProductById để xóa sản phẩm
            boolean isDeleted = productAdminDao.deleteProductById(productIDToDelete);

            // Kiểm tra kết quả và in ra thông báo
            if (isDeleted) {
                System.out.println("Sản phẩm với ID " + productIDToDelete + " đã được xóa thành công.");
            } else {
                System.out.println("Không tìm thấy sản phẩm với ID " + productIDToDelete + ".");
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ
            System.err.println("Đã xảy ra lỗi khi xóa sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
