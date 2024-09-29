/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author thanh
 */
public class Cart extends Product{
    private int cartID;      // Khóa chính
    private int productID;   // Khóa ngoại tham chiếu đến bảng Product
    private int accountID;   // Khóa ngoại tham chiếu đến bảng Account
    private int colorID; // Có thể null
    private int sizeID;  // Có thể null
    private int quantity;// Có thể null
    private String image;// Có thể null
    private Product product;

    public Cart() {
    }

    // Constructor
    public Cart(int cartID, int productID, int accountID, Integer colorID, Integer sizeID, Integer quantity) {
        this.cartID = cartID;
        this.productID = productID;
        this.accountID = accountID;
        this.colorID = colorID;
        this.sizeID = sizeID;
        this.quantity = quantity;
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

    public Integer getColorID() {
        return colorID;
    }

    public void setColorID(Integer colorID) {
        this.colorID = colorID;
    }

    public Integer getSizeID() {
        return sizeID;
    }

    public void setSizeID(Integer sizeID) {
        this.sizeID = sizeID;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
