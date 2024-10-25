/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author thanh
 */
public class OrderDetail2 {
    private int orderDetailID;
    private Order2 orderID;
    private Product productID;
    private Stock stockID;
    private int quantity;
    private double unitPrice;
    private String color; // Thêm thuộc tính cho màu sắc
    private String size;  // Thêm thuộc tính cho kích thước
    private String imageUrl;
    
    public OrderDetail2() {
    }

    public OrderDetail2(int orderDetailID, Order2 orderID, Product productID, Stock stockID, int quantity, double unitPrice, String color, String size, String imageUrl) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.stockID = stockID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.color = color;
        this.size = size;
        this.imageUrl = imageUrl;
    }

    

  
     
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Order2 getOrderID() {
        return orderID;
    }

    public void setOrderID(Order2 orderID) {
        this.orderID = orderID;
    }

   

    public Product getProductID() {
        return productID;
    }

    public void setProductID(Product productID) {
        this.productID = productID;
    }

    public Stock getStockID() {
        return stockID;
    }

    public void setStockID(Stock stockID) {
        this.stockID = stockID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

  public void setColor(String color) {
        this.color = color;
    }
    
    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    public String getSize() {
        return size;
    }
}
