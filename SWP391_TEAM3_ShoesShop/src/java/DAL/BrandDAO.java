package DAL;

import Models.Brand;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO extends DBContext {
    public void insertBrand(Brand brand) {
        //  truy vấn INSERT 
        String sql = "INSERT INTO Brand (bName) VALUES (?)"; // Xóa brandID khỏi câu lệnh SQL
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, brand.getBrandName()); // chỉ cần brandName
            
            ps.executeUpdate();
            // chạy query
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức kiểm tra thương hiệu đã tồn tại
    public boolean brandExists(String brandName) {
        String sql = "SELECT COUNT(*) FROM Brand WHERE brandName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, brandName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // trả về true nếu tồn tại, ngược lại false
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // nếu không có lỗi và không tìm thấy
    }
    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();
        String sql = "SELECT brandID, brandName FROM Brand";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setBrandID(rs.getInt("brandID"));
                brand.setBrandName(rs.getString("brandName"));
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brands;
    }
}
