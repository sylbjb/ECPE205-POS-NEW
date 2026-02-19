public class products {

    int sku, quantity;
    String name;
    float price, amount;

    //Constructor to be used by the productScreen Class
    public products(int sku, String name, float price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    //Constructor to be used by the orderScreen Class
    public products(int sku, int quantity, String name, float price, float amount) {
        this.sku = sku;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }


}
