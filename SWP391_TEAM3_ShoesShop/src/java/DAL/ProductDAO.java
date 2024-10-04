/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Image;
import Models.Product;
import Models.Size;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext{
    //Return a list of all products
    public List<Product> getAllProducts(){
        List<Product> list= new ArrayList<>();
        String sql="select * from Product";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while(rs.next()){                
                int productId=rs.getInt("ProductID");
                String productName=rs.getString("ProductName");
                String description=rs.getString("Description");
                double price=rs.getDouble("Price");
                int brandId=rs.getInt("BrandID");
                String avatarP=rs.getString("AvatarP");
                Product p = new Product(productId, productName, description, price, brandId, avatarP);
                list.add(p);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't get products"); 
            return new ArrayList<>();
        }
        return list;
    }
    
    //Return a list of pages containing n products each
    public List<List<Product>> getAllProductsPaginated(List<Product> list, int n){
        List<List<Product>> pageList=new ArrayList<>();
        if(list.isEmpty()){
            System.out.println("No products exist");
        }
        else{
            List<Product> page=new ArrayList<>();
            for(int i=0;i<list.size();i++){
                //Add the page every n products, excluding page 0
                if(i%n==0 && i>0){
                    pageList.add(page);
                    page=new ArrayList<>();
                }
                page.add(list.get(i));
            }
            //Add the last page
            pageList.add(page);
        }
        return pageList;
    }
    
    //Return a list of all product IDs
    public List<Integer> getAllProductIds(){
        List<Integer> list= new ArrayList<>();
        String sql="select ProductID from Product";
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while(rs.next()){                
                int productId=rs.getInt("ProductID");
                list.add(productId);
            }
        }
        catch (SQLException e) {
            System.out.println("Can't get products"); 
            return new ArrayList<>();
        }
        return list;
    }
    
    public Product getProductById(int productId){
        List<Product> list = getAllProducts();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getProductId()==productId){
                return list.get(i);
            }
        }
        return null;
    }
    
    //Add a product into the DB
    public boolean addProduct(Product p){
        if(getProductById(p.getProductId())!=null){
            System.out.println("Product exists");
            return false;            
        }
        else{
            String sql="insert into Product values(?, ?, ?, ?, ?, ?)";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(1, p.getProductId());
                pre.setString(2, p.getProductName());
                pre.setString(3, p.getDescription());
                pre.setDouble(4, p.getPrice());
                pre.setInt(5, p.getBrandId());
                pre.setString(6, p.getAvatarP());
                pre.executeUpdate();
                return true;
            }
            catch(SQLException e){
                System.out.println("Can't add product");
                return false;
            }
        }
    }
    
    //Update a product in the DB
    public boolean updateProduct(Product p){
        if(getProductById(p.getProductId())==null){
            System.out.println("Product doesn't exist");
            return false;            
        }
        else{
            String sql="update Product\n"
                     + "set ProductName=?, Description=?, Price=?, BrandID=?, AvatarP=?\n"
                     + "where ProductID=?";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(8, p.getProductId());
                pre.setString(1, p.getProductName());
                pre.setString(2, p.getDescription());
                pre.setDouble(3, p.getPrice());
                pre.setInt(4, p.getBrandId());
                pre.setString(5, p.getAvatarP());
                pre.executeUpdate();
                return true;
            }
            catch(SQLException e){
                System.out.println("Can't update product");
                return false;
            }
        }
    }
    
    //Delete a product form the DB
    public boolean deleteProduct(Product p){
        if(getProductById(p.getProductId())==null){
            System.out.println("Product doesn't exist");
            return false;            
        }
        else{
            String sql="delete from Product where ProductID=?";
            try{
                PreparedStatement pre=connection.prepareStatement(sql);
                pre.setInt(1, p.getProductId());
                pre.executeUpdate();
                return true;
            }
            catch(SQLException e){
                System.out.println("Can't delete product");
                return false;
            }
        }        
    }
    
    public List<Models.Stock> getProductStock(Product p){
        List<Models.Stock> Stocks=new ArrayList<>();
        String sql="select ps.* from Product p join ProductStock ps on p.ProductID=ps.ProductID\n"
                 + "where p.ProductID=?";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setInt(1, p.getProductId());
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                int stockId = rs.getInt("StockId");
                int colorId=rs.getInt("ColorID");
                int SizeID=rs.getInt("SizeID");
                int productId=rs.getInt("ProductID");
                int quantity = rs.getInt("quantity");
                Models.Stock c = new Models.Stock(stockId, colorId, SizeID, productId, quantity);
                Stocks.add(c);
            }
        }
        catch(SQLException e){
            System.out.println("Can't get colors");
        }
        return Stocks;
    }
    
    public List<Models.Color> getProductColors(Product p){
        List<Models.Color> colors=new ArrayList<>();
        String sql="select pc.* from Product p join Product_Color pc on p.ProductID=pc.ProductID\n"
                 + "where p.ProductID=?";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setInt(1, p.getProductId());
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                int colorId=rs.getInt("ColorID");
                int productId=rs.getInt("ProductID");
                String color=rs.getString("Color");
                Models.Color c = new Models.Color(colorId, productId, color);
                colors.add(c);
            }
        }
        catch(SQLException e){
            System.out.println("Can't get colors");
        }
        return colors;
    }
    public List<Size> getProductSizes(Product p){
        List<Size> sizes=new ArrayList<>();
        String sql="select ps.* from Product p join Product_Size ps on p.ProductID=ps.ProductID\n"
                 + "where p.ProductID=?";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setInt(1, p.getProductId());
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
            System.out.println("Can't get sizes");
        }
        return sizes;
    }
    public List<Image> getProductImages(Product p){
        List<Image> images=new ArrayList<>();
        String sql="select i.* from Product p join Product_Image i on p.ProductID=i.ProductID\n"
                 + "where p.ProductID=?";
        try{
            PreparedStatement pre=connection.prepareStatement(sql);
            pre.setInt(1, p.getProductId());
            ResultSet rs=pre.executeQuery();
            while(rs.next()){
                int imageId=rs.getInt("ImageID");
                String imageUrl=rs.getString("ImageUrl");
                Image i = new Image(imageId, imageUrl);
                images.add(i);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Can't get images");
        }
        return images;
    }
    
    //Return a list of products that contain the one of the keywords in their name, description, brand, color or size
    //The keyword search query is handled in the servlet
    public List<Product> searchProductByKeywords(List<String> keywords){
        List<Product> list=new ArrayList<>();
        //Construct the sql query
        StringBuilder sb = new StringBuilder("select p.* from Product p\n"
                + "left join Brand c on p.BrandID=c.BrandID \n"
                + "left join Product_Color pc on p.ProductID=pc.ProductID\n"
                + "left join Product_Size ps on p.ProductID=ps.ProductID\n"
                + "where\n");
        //Make a slot for each keyword
        for(int i=0;i<keywords.size();i++){
            sb.append("concat(p.ProductName, ' ', p.Description, ' ', pc.Color, ' ', ps.Size) LIKE ?");
            if(i!=keywords.size()-1){
                sb.append(" or \n");
            }
        }
        String sql=sb.toString();
        try {
            PreparedStatement pre=connection.prepareStatement(sql);
            //Set the keywords into their slots
            for(int i=0;i<keywords.size();i++){
                pre.setString(i+1, "%"+keywords.get(i)+"%");
            }
            pre.executeQuery();
            ResultSet rs = pre.getResultSet();
            while(rs.next()){                
                int productId=rs.getInt("ProductID");
                //Check for distinct
                if(productIdExists(productId, list)){
                    continue;
                }
                String productName=rs.getString("ProductName");
                String description=rs.getString("Description");
                double price=rs.getDouble("Price");
                int brandId=rs.getInt("BrandID");
                String avatarP=rs.getString("AvatarP");
                Product p = new Product(productId, productName, description, price, brandId, avatarP);
                list.add(p);
            }
        }
        catch (SQLException e) {
            System.out.println("Can't get products with keywords"); 
            return new ArrayList<>();
        }
        return list;
    }
     Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

 public List<Product> searchByName(String txtSearch) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT * FROM Product WHERE [ProductName] LIKE ?";
    
    try {
        conn = new DBContext().connection; // Open connection with SQL
        ps = conn.prepareStatement(query);
        ps.setString(1, "%" + txtSearch + "%");
        rs = ps.executeQuery();
        
        while (rs.next()) {
            // Assuming the Product constructor matches the fields in the Product table
            list.add(new Product(
                rs.getInt("ProductID"), // Field for Product ID
                rs.getString("ProductName"), // Field for Product Name
                rs.getString("Description"), // Field for Description
                rs.getDouble("Price"), // Field for Price
                rs.getInt("BrandID"), // Field for Brand ID
                rs.getString("AvatarP") // Field for Avatar Path
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Print stack trace for debugging
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close(); // Close resources
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
    return list;
}

    //Check if an id (and by extension, a product) already exists in the list
    public boolean productIdExists(int productId, List<Product> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getProductId()==productId){
                return true;
            }
        }
        return false;
    }
    
    public void sortProductsById(List<Product> list){
        list.sort(Comparator.comparingInt(Product::getProductId));
    }
    
    public void printAllProducts(List<Product> list){
        for(int i=0;i<list.size();i++){
            Product p = list.get(i);
            System.out.printf("%-3d | %-30s | %-70s | %-8.2f | %-2d | %-20s%n",
                    p.getProductId(),
                    p.getProductName(),
                    p.getDescription(),
                    p.getPrice(),
                    p.getBrandId(),
                    p.getAvatarP()
            );
        }
        System.out.println();
    }
    
    public static void main(String[] args){
        ProductDAO prd = new ProductDAO();
//        List<String> keywords=new ArrayList();
//        keywords.add("red");
//        keywords.add("adidas");
//        keywords.add("20");
//        List<Product> list = prd.searchProductByKeywords(keywords);
//        prd.sortProductsById(list);
//        prd.printAllProducts(list);
        List<List<Product>> pageList=prd.getAllProductsPaginated(prd.getAllProducts(), 12);
        for(int i=0;i<pageList.size();i++){
            System.out.println("Page"+(i+1));
            prd.printAllProducts(pageList.get(i));
            System.out.println();
        }
    }
}