/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Color;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class ColorDAO extends DBContext{
    //Return a list of colors for each product
    public List<ProductColor> getAllColorsByProduct(){
        List<ProductColor> list=new ArrayList<>();
        ProductDAO prd=new ProductDAO();
        List<Integer> productIdList = prd.getAllProductIds();
        for(int i=0;i<productIdList.size();i++){
            List<Color> colors=new ArrayList<>();
            String sql="select * from Product_Color\n"
                     + "where ProductID=?";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(1, productIdList.get(i));
                ResultSet rs=pre.executeQuery();
                while(rs.next()){
                    int colorId=rs.getInt("ColorID");
                    int productId=rs.getInt("ProductID");
                    String color=rs.getString("Color");
                    Color s = new Color(colorId, productId, color);
                    colors.add(s);
                }
            }
            catch(SQLException e){
                System.out.println("Can't get color of productId: "+productIdList.get(i));
            }
            list.add(new ProductColor(colors, productIdList.get(i)));
        }
        return list;
    }
    
        public class ProductColor{
            private List<Color> colors;
            private int productId;
            public ProductColor(){}
            public ProductColor(List<Color> colors, int productId) {
                this.colors = colors;
                this.productId = productId;
            }

            public List<Color> getColors() {
                return colors;
            }

            public void setColors(List<Color> colors) {
                this.colors = colors;
            }

            public int getProductId() {
                return productId;
            }

            public void setProductId(int productId) {
                this.productId = productId;
            }

            @Override
            public String toString() {
                StringBuilder colors = new StringBuilder();
                for(int i=0;i<this.colors.size();i++){
                    colors.append(this.colors.get(i));
                    if(i<this.colors.size()-1){
                        colors.append(", ");
                    }
                }
                return colors.toString();
            }

        }
    
    //Return hashmap instead
    /*
    public Map<Integer, List<Color>> getAllColorsByProductMap(){
        ProductDAO prd=new ProductDAO();
        Map<Integer, List<Color>> colorMap = new HashMap<>();
        List<Integer> productIdList = prd.getAllProductIds();
        for(int i=0;i<productIdList.size();i++){
            List<Color> colors=new ArrayList<>();
            String sql="select * from Product_Color\n"
                     + "where ProductID=?";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(1, productIdList.get(i));
                ResultSet rs=pre.executeQuery();
                while(rs.next()){
                    int colorId=rs.getInt("ColorID");
                    int productId=rs.getInt("ProductID");
                    String color=rs.getString("Color");
                    Color s = new Color(colorId, productId, color);
                    colors.add(s);
                }
            }
            catch(SQLException e){
                System.out.println("Can't get color of productId: "+productIdList.get(i));
            }
            colorMap.put(productIdList.get(i), colors);
        }
        return colorMap;
    }
    */
    
    public void printAllColorsByProduct(List<ProductColor> list){
       for(int i=0;i<list.size();i++){
            ProductColor pc = list.get(i);
            System.out.printf("%-3d | %s%n",
                    pc.getProductId(),
                    pc.toString()
            );
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        ColorDAO cld=new ColorDAO();
        cld.printAllColorsByProduct(cld.getAllColorsByProduct());
    }
}
