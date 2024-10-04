/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Size;
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
public class SizeDAO extends DBContext{
    //Return a list of sizes for each product
    public List<ProductSize> getAllSizesByProduct(){
        List<ProductSize> list=new ArrayList<>();
        ProductDAO prd=new ProductDAO();
        List<Integer> productIdList = prd.getAllProductIds();
        for(int i=0;i<productIdList.size();i++){
            List<Size> sizes=new ArrayList<>();
            String sql="select * from Product_Size\n"
                     + "where ProductID=?";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(1, productIdList.get(i));
                ResultSet rs=pre.executeQuery();
                while(rs.next()){
                    int sizeId=rs.getInt("SizeID");
                    int productId=rs.getInt("ProductID");
                    int size=rs.getInt("Size");
                    Size s = new Size(sizeId, productId, size);
                    sizes.add(s);
                }
            }
            catch(SQLException e){
                System.out.println("Can't get size of productId: "+productIdList.get(i));
            }
            list.add(new ProductSize(sizes, productIdList.get(i)));
        }
        return list;
    }
    
    public class ProductSize{
        private List<Size> sizes;
        private int productId;
        public ProductSize(){}
        public ProductSize(List<Size> sizes, int productId) {
            this.sizes = sizes;
            this.productId = productId;
        }

        public List<Size> getSizes() {
            return sizes;
        }

        public void setSizes(List<Size> sizes) {
            this.sizes = sizes;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }
        
        @Override
        public String toString() {
            StringBuilder sizes = new StringBuilder();
            for (int i = 0; i < this.sizes.size(); i++) {
                sizes.append(this.sizes.get(i));
                if (i < this.sizes.size() - 1) {
                    sizes.append(", ");
                }
            }
            return sizes.toString();
        }
    }
    
    //Return hashmap instead
    /*
    public Map<Integer, List<Size>> getAllSizesByProductMap(){
        ProductDAO prd=new ProductDAO();
        Map<Integer, List<Size>> sizeMap = new HashMap<>();
        List<Integer> productIdList = prd.getAllProductIds();
        for(int i=0;i<productIdList.size();i++){
            List<Size> sizes=new ArrayList<>();
            String sql="select * from Product_Size\n"
                     + "where ProductID=?";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(1, productIdList.get(i));
                ResultSet rs=pre.executeQuery();
                while(rs.next()){
                    int sizeId=rs.getInt("SizeID");
                    int productId=rs.getInt("ProductID");
                    int size=rs.getInt("Size");
                    int quantity=rs.getInt("Quantity");
                    Size s = new Size(sizeId, productId, size, quantity);
                    sizes.add(s);
                }
            }
            catch(SQLException e){
                System.out.println("Can't get size of productId: "+productIdList.get(i));
            }
            sizeMap.put(productIdList.get(i), sizes);
        }
        return sizeMap;
    }
    */
    
    public void printAllSizesByProduct(List<ProductSize> list){
       for(int i=0;i<list.size();i++){
            ProductSize pc = list.get(i);
            System.out.printf("%-3d | %s%n",
                    pc.getProductId(),
                    pc.toString()
            );
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        SizeDAO cld=new SizeDAO();
        cld.printAllSizesByProduct(cld.getAllSizesByProduct());
    }
}
