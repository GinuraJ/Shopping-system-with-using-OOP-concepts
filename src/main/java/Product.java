abstract public class Product {
    private String productID;
    private String productName;
    private int AvailableAmount;
    private double productPrice;

    public Product(){};
    public Product(String productID, String productName, int availableAmount, double productPrice) {
        this.productID = productID;
        this.productName = productName;
        AvailableAmount = availableAmount;
        this.productPrice = productPrice;
    }
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailableAmount() {
        return AvailableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        AvailableAmount = availableAmount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    abstract void addProductToSystem();

    @Override
    public String toString() {
        return "productID='" + productID + '\'' + ", productName='" + productName + '\'' + ", AvailableAmount=" + AvailableAmount + ", productPrice=" + productPrice + ',';
    }
}
