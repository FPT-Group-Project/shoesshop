package DAL;

import Models.Brand;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListBrandDao extends DBContext {
    
    // Hàmlấy danh sách thương hiệu
    public List<Brand> getListBrand() {
        List<Brand> listBrands = new ArrayList<>();
        String sql = "SELECT * FROM Brand"; 

        try (
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

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
        ListBrandDao aa = new ListBrandDao();
       List<Brand> list =  aa.getListBrand();
        for (Brand brand : list) {
            System.out.println(brand.toString());
        }
    }
}
