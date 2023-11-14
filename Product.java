public class Product {
    private int productID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageURL;


    public Product(int productID, String name, double price, int quantity, String imageURL, String description) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.description = description;
    }

    public int getProductID(){
        return productID;
    }
    
    public double getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getQuantity(){
        return quantity;
    }
    
    public String getImageURL(){
        return imageURL;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }


}
