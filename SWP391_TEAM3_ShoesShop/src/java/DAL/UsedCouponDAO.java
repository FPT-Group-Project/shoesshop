/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.UsedCoupon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class UsedCouponDAO extends DBContext{
    public List<UsedCoupon> getAllUsedCoupons(){
        List<UsedCoupon> list = new ArrayList<>();
        String sql="select * from usedCoupons";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while(rs.next()){                
                int codeId=rs.getInt("CodeID");
                String codeName=rs.getString("CodeName");
                double discount=rs.getDouble("Discount");
                String couponType=rs.getString("CouponType");
                int quantity=rs.getInt("Quantity");
                UsedCoupon u = new UsedCoupon(codeId, codeName, discount, couponType, quantity);
                list.add(u);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't get used coupons");
        }
        return list;
    }
    public UsedCoupon getCouponByCodeName(String codeName) {
        String query = "SELECT * FROM UsedCoupons WHERE CodeName = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, codeName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int codeID = rs.getInt("CodeID");
                String name = rs.getString("CodeName");
                Double discount = rs.getDouble("Discount");
                String couponType = rs.getString("CouponType");
                int quantity = rs.getInt("Quantity");

                return new UsedCoupon(codeID, name, discount, couponType, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Không tìm thấy mã giảm giá
    }
    public static void main(String[] args){
        UsedCouponDAO ucd=new UsedCouponDAO();
          

            // Gọi phương thức getCouponByCodeName để lấy thông tin mã giảm giá
            String codeName = "FREESHIP";  // Thay đổi giá trị này với mã giảm giá bạn muốn tìm
            UsedCoupon coupon = ucd.getCouponByCodeName(codeName);

            // In ra thông tin mã giảm giá nếu tìm thấy
            if (coupon != null) {
                System.out.println("Coupon Found:");
                System.out.println("Code ID: " + coupon.getCodeId());
                System.out.println("Code Name: " + coupon.getCodeName());
                System.out.println("Discount: " + coupon.getDiscount());
                System.out.println("Coupon Type: " + coupon.getCouponType());
                System.out.println("Quantity: " + coupon.getQuantity());
            } else {
                System.out.println("Coupon with code " + codeName + " not found.");
            }
        } 
        }
    

