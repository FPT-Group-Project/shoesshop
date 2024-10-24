package Models;

public class ProductStock {
    private int stockId;
    private int productID;
    private int sizeID;
    private int colorID;
    private int quantity;

    public ProductStock() {}

    public ProductStock(int productID, int sizeID, int colorID, int quantity) {
        this.productID = productID;
        this.sizeID = sizeID;
        this.colorID = colorID;
        this.quantity = quantity;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
}
