/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author thanh
 */
public class Wishlist {
    private int wishlistId;
    private int accountId;
    private Product productId;
    private Date addedDate;

    public Wishlist() {
    }

    public Wishlist(int wishlistId, int accountId, Product productId, Date addedDate) {
        this.wishlistId = wishlistId;
        this.accountId = accountId;
        this.productId = productId;
        this.addedDate = addedDate;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
    
}
