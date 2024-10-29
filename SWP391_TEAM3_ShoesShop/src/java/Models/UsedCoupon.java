/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Admin
 */
public class UsedCoupon {
    private int codeId;
    private String codeName;
    private double discount;
    private String couponType;
    private int quantity;
    
    public UsedCoupon(){}
    public UsedCoupon(int codeId, String codeName, double discount, String couponType, int quantity) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.discount = discount;
        this.couponType = couponType;
        this.quantity = quantity;
    }
    
    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
