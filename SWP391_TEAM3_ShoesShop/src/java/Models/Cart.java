/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.math.BigDecimal;

/**
 *
 * @author thanh
 */
public class Cart extends Product{
    private int cartID;      // Khóa chính
    private int productID;   // Khóa ngoại tham chiếu đến bảng Product
    private int accountID;   // Khóa ngoại tham chiếu đến bảng Account
    private int StockID;
    private int quantity;// Có thể null
    private String image;// Có thể null
    private Product product;
    private Stock stock;
    private BigDecimal discount;  // Thêm trường Discount
    public Cart() {
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    // Constructor
    public Cart(int cartID, int productID, int accountID, Integer StockID, Integer quantity) {
        this.cartID = cartID;
        this.productID = productID;
        this.accountID = accountID;
        this.StockID = StockID;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // Getters và Setters
    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getStockID() {
        return StockID;
    }

    public void setStockID(int StockID) {
        this.StockID = StockID;
    }
    

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
