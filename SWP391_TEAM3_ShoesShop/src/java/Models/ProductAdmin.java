/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

public class ProductAdmin {
    private int productID;
    private String productName;
    private String description;
    private int quantity;
    private double price;
    private Brand brandID;
    private String avatarP;

    public ProductAdmin() {
    }

    public ProductAdmin(int productID, String productName, String description, int quantity, double price, Brand brandID, String avatarP) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brandID = brandID;
        this.avatarP = avatarP;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Brand getBrandID() {
        return brandID;
    }

    public void setBrandID(Brand brandID) {
        this.brandID = brandID;
    }

    public String getAvatarP() {
        return avatarP;
    }

    public void setAvatarP(String avatarP) {
        this.avatarP = avatarP;
    }

    @Override
    public String toString() {
        return "ProductAdmin{" + "productID=" + productID + ", productName=" + productName + ", description=" + description + ", quantity=" + quantity + ", price=" + price + ", brandID=" + brandID + ", avatarP=" + avatarP + '}';
    }

    
    
}