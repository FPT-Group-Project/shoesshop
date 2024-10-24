package Models;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int orderID;
    private int accountID;
    private String address;
    private int historyCouponsID;
    private BigDecimal totalPrice;
    private Date orderDate;
    private Date arrivalDate;
    private int statusID;
    private String paymentStatus;

    public Order() {
    }

    public Order(int orderID, int accountID, String address, int historyCouponsID, BigDecimal totalPrice, Date orderDate, Date arrivalDate, int statusID, String paymentStatus) {
        this.orderID = orderID;
        this.accountID = accountID;
        this.address = address;
        this.historyCouponsID = historyCouponsID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.arrivalDate = arrivalDate;
        this.statusID = statusID;
        this.paymentStatus = paymentStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHistoryCouponsID() {
        return historyCouponsID;
    }

    public void setHistoryCouponsID(int historyCouponsID) {
        this.historyCouponsID = historyCouponsID;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    
}
