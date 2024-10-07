package DAL;

import Models.Brand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListBrandDao extends DBContext {
 
  public boolean updateName(String name ,int id){
  String sql = "UPDATE [Brand]\n" +
"SET [BrandName] = ?\n" +
"WHERE [BrandID] = ?";
      try {
                      PreparedStatement stmt = connection.prepareStatement(sql);
stmt.setString(1, name);
          stmt.setInt(2,id );

                  int rowsAffected = stmt.executeUpdate();
     return rowsAffected>0;
      } catch (Exception e) {
          
      }
  return false;
  }
    
    public Brand getNameBrand(int id) {
        Brand brand = new Brand();
        String sql = "SELECT  [BrandID]\n"
                + "      ,[BrandName]\n"
                + "  FROM [Brand] where BrandID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
           
            while(rs.next()){
            brand.setBrandID(rs.getInt("BrandID"));
                    
             brand.setBrandName(rs.getString("BrandName"));
            
            }
        } catch (Exception e) {
            return null;
        }
        
    return brand;
    }

    // Hàm lấy danh sách brands
    public List<Brand> getListBrand() {
        List<Brand> listBrands = new ArrayList<>();
        String sql = "SELECT * FROM Brand";

        try (
                PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int brandID = rs.getInt("BrandID");
                String brandName = rs.getString("BrandName");

                Brand brand = new Brand(brandID, brandName);
                listBrands.add(brand);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBrands;
    }

    // tìm kiếm = key
    public List<Brand> getListBrand(String searchKeyword) {
        List<Brand> listBrands = new ArrayList<>();
        String sql = "SELECT * FROM Brand WHERE BrandName LIKE ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + searchKeyword + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int brandID = rs.getInt("BrandID");
                    String brandName = rs.getString("BrandName");

                    Brand brand = new Brand(brandID, brandName);
                    listBrands.add(brand);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBrands;
    }

    public static void main(String[] args) {
        ListBrandDao aa = new ListBrandDao();        boolean a = aa.updateName("iiii", 8);

        List<Brand> list = aa.getListBrand();
        Brand brand = aa.getNameBrand(1);
         System.out.println(a);
    }
}
