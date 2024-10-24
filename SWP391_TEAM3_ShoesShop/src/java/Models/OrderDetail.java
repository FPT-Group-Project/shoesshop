package Models;

import java.math.BigDecimal;

public class OrderDetail {
    private int orderDetailID;
    private int orderID;
    private int productID;
    private int stockID;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailID, int orderID, int productID, int stockID, int quantity, BigDecimal unitPrice) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.stockID = stockID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
