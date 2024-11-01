/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Banner;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class BannerDAO extends DBContext{
    public List<Banner> getAllBanners(){
        List<Banner> list=new ArrayList<>();
        String sql="select * from Banner";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                int bannerId=rs.getInt("id");
                String image=rs.getString("Image");
                String link=rs.getString("link");
                Banner b = new Banner(bannerId, image, link);
                list.add(b);
            }
        }
        catch(SQLException e){
            System.out.println("Can't get banners");
        }
        return list;
    }
    
    public List<Banner> getNewestBanners(int n){
        List<Banner> list=new ArrayList<>();
        String sql="select top "+ n + " * from Banner\n"
                 + "order by id desc";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                int bannerId=rs.getInt("id");
                String image=rs.getString("Image");
                String link=rs.getString("link");
                Banner b = new Banner(bannerId, image, link);
                list.add(b);
            }
        }
        catch(SQLException e){
            System.out.println("Can't get banners");
        }
        return list;
    }
    
    
    
    public static void main(String[] args){
        BannerDAO bnd=new BannerDAO();
        List<Banner> list=bnd.getNewestBanners(2);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getImage() + " | " + list.get(i).getLink());
        }
    }
}
