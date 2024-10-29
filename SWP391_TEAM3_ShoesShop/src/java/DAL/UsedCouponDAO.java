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
    
    public static void main(String[] args){
        UsedCouponDAO ucd=new UsedCouponDAO();
        List<UsedCoupon> list=ucd.getAllUsedCoupons();
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getCodeName());
        }
    }
}
