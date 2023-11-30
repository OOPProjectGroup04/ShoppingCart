import java.util.ArrayList;
import java.util.List;

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

    // Static method to create and return a list of products
    public static List<Product> getSampleProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(101, "Laptop", 999.99, 10, "path/to/laptop/image.jpg", "A high-performance laptop."));
        products.add(new Product(102, "Smartphone", 499.99, 15, "path/to/smartphone/image.jpg", "An innovative smartphone with the best camera."));
        products.add(new Product(103, "DashCam", 99.99, 30, "path/to/dashcam/image.jpg", "A Dashcam for your car."));
        products.add(new Product(3, "Bluetooth Headphones", 89.99, 25, "path/to/headphones/image.jpg", "Bluetooth headphones with noise cancellation."));
        products.add(new Product(4, "Electric Toothbrush", 39.99, 40, "path/to/toothbrush/image.jpg", "Rechargeable toothbrush with smart timer."));
        products.add(new Product(5, "Gaming Laptop", 1299.99, 30, "path/to/console/image.jpg", "The latest gaming laptop with 24GB Ram and 1TB SSD."));
        products.add(new Product(6, "Smart Watch", 199.99, 20, "path/to/watch/image.jpg", "A smartwatch with health and fitness tracking."));
        products.add(new Product(7, "Tablet", 329.99, 15, "path/to/tablet/image.jpg", "A versatile tablet perfect for work and play."));
        products.add(new Product(8, "Bluetooth Mouse", 24.99, 50, "path/to/mouse/image.jpg", "A comfortable mouse for everyday use."));
        products.add(new Product(9, "E-Reader", 129.99, 30, "path/to/ereader/image.jpg", "An e-reader with a paper-like display."));
        products.add(new Product(11, "Portable Speaker", 59.99, 35, "path/to/speaker/image.jpg", "A portable speaker with excellent sound quality."));
        products.add(new Product(12, "Fitness Tracker", 49.99, 45, "path/to/tracker/image.jpg", "Track your daily activity and sleep."));
        products.add(new Product(13, "Espresso Machine", 249.99, 15, "path/to/espresso/image.jpg", "Brew cafe-quality espresso at home with ease."));
        products.add(new Product(14, "Yoga Mat", 19.99, 30, "path/to/yogamat/image.jpg", "Eco-friendly yoga mat with non-slip surface."));
        products.add(new Product(15, "LED Desk Lamp", 45.99, 20, "path/to/desklamp/image.jpg", "Adjustable desk lamp with multiple brightness settings."));
        products.add(new Product(17, "Cookware Set", 129.99, 25, "path/to/cookware/image.jpg", "Stainless steel cookware set for all your cooking needs."));
        products.add(new Product(18, "Sunglasses", 89.99, 50, "path/to/sunglasses/image.jpg", "Stylish sunglasses with UV protection."));
        products.add(new Product(19, "Backpack", 59.99, 35, "path/to/backpack/image.jpg", "Durable backpack for travel and everyday use."));
        products.add(new Product(20, "Leather Wallet", 49.99, 45, "path/to/wallet/image.jpg", "Elegant leather wallet with RFID blocking."));
        products.add(new Product(22, "Noise-cancelling Earplugs", 25.99, 50, "path/to/earplugs/image.jpg", "Reusable earplugs with superior noise cancellation."));

        return products;
    }

List<Product> productList = Product.getSampleProducts();
}
