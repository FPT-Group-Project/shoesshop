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
public void deleteProductById(int productID) {
    String sql = "DELETE FROM [dbo].[Product] WHERE [ProductID] = ?";
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
            
           stmt.setInt(1, productID);
           ResultSet rs = stmt.executeQuery();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Phương thức lấy danh sách sản phẩm dành cho Admin
  public List<ProductAdmin> getProductListForAdmin(int page, Integer brandID) {
    List<ProductAdmin> productList = new ArrayList<>();
    int itemsPerPage = 5; 
    int offset = (page - 1) * itemsPerPage; 

    
    String sql;
    if (brandID != null) {
        sql = "SELECT *\n" +
              "FROM [dbo].[Product] p\n" +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID\n" +
              "WHERE p.BrandID = ?\n" +
              "ORDER BY p.ProductID\n" +
              "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    } else {
        sql = "SELECT *\n" +
              "FROM [dbo].[Product] p\n" +
              "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID\n" +
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
                    rs.getInt("Quantity"),
                    rs.getDouble("Price"),
                    brand,
                    rs.getString("AvatarP")
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

    String sql = "SELECT *\n" +
                 "FROM [dbo].[Product] p\n" +
                 "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID\n" +
                 "WHERE p.ProductName LIKE ?\n";

    if (brandID != null) {
        sql += "AND p.BrandID = ?\n";
    }

    sql += "ORDER BY p.ProductID\n" +
           "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);

        stmt.setString(1, "%" + keyWord + "%"); 

        if (brandID != null) {
            stmt.setInt(2, brandID); 
            stmt.setInt(3, offset);
            stmt.setInt(4, itemsPerPage); 
        } else {
            stmt.setInt(2, offset); 
            stmt.setInt(3, itemsPerPage); 
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
                    rs.getInt("Quantity"),
                    rs.getDouble("Price"),
                    brand,
                    rs.getString("AvatarP")
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

public int countProductListForAdmin(Integer brandID, String keyWord) {
    int totalProducts = 0; 


    String sql = "SELECT COUNT(*)\n" +
                 "FROM [dbo].[Product] p\n" +
                 "JOIN [dbo].[Brand] c ON p.BrandID = c.BrandID\n" +
                 "WHERE p.ProductName LIKE ?\n"; 

    // Nếu có brandID, thêm điều kiện WHERE
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


    public static void main(String[] args) {
    ListProductAdminDao viewListProductAdmin = new ListProductAdminDao();

   
    int page = 1;
    
    List<ProductAdmin> productsPage2 = viewListProductAdmin.getProductListForAdmin(page, null);
    List< Brand> listB= viewListProductAdmin.getListBrand();
     int co =  viewListProductAdmin.countProductListForAdmin(1, "");
               System.out.println("sL CATE 1 "+co);
    if (productsPage2.isEmpty()) {
        System.out.println("No products found on page " + page + ".");
    } else {
                for (Brand product : listB) {
            System.out.println(product.toString());
        }
        for (ProductAdmin product : productsPage2) {
            System.out.println(product.toString());
        }
    }
}


}
