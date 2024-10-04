/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author TGDD
 */
public class Stock {
    int stockId;
    int productID;
    int sizeID;
	int size;
	String color;
    int colorID;
    int quantity;

    public Stock() {
    }

    public Stock(int StockId, int ProductID, int SizeID, int ColorID, int quantity) {
        this.stockId = StockId;
        this.productID = ProductID;
        this.sizeID = SizeID;
        this.colorID = ColorID;
        this.quantity = quantity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int StockId) {
        this.stockId = StockId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int ProductID) {
        this.productID = ProductID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int SizeID) {
        this.sizeID = SizeID;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int ColorID) {
        this.colorID = ColorID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
