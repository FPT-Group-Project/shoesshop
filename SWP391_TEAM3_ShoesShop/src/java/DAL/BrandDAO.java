package DAL;

import Models.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BrandDAO extends DBContext {
    public void insertBrand(Brand brand) {
        //  truy vấn INSERT 
        String sql = "INSERT INTO Brand (brandID, brandName) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, brand.getBrandID());
            ps.setString(2, brand.getBrandName());
            
            ps.executeUpdate();
            //chạy query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
