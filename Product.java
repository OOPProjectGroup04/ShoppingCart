import java.util.ArrayList;
import java.util.List;
/**
 * class for storing information about the products
 * @author freddy ingle
 * @author george martinez
 * @author Bryan Cooke
 * contributor: Sydney tivoli provided the image URLs
 */
public class Product {
    private int productID;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imageURL;
    private static List<Product> products = new ArrayList<>();
    public Product(int productID, String name, double price, int quantity, String imageURL, String description) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.description = description;
    }

    /**
     * 
     * @return product id
     */
    public int getProductID(){
        return productID;
    }
    /**
     * 
     * @param productID
     * @return product
     */
    public static Product getProduct(int productID) {
        for (Product product : products) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    /**
     * 
     * @return price
     */
    public double getPrice(){
        return price;
    }
    /**
     * 
     * @return name
     */
    public String getName(){
        return name;
    }
    /**
     * 
     * @return desc
     */
    public String getDescription(){
        return description;
    }
    /**
     * 
     * @return quantity
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * 
     * @return imageURL
     */
    public String getImageURL(){
        return imageURL;
    }
    /**
     * 
     * @param imageURL
     */
    public void setImageURL(String imageURL) {this.imageURL = imageURL;}
    /**
     * 
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }
    /**
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * 
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    /**
     * 
     * @return
     */
    public boolean isAvailable() {return quantity > 0;}
    /**
     * 
     * @return
     */
    public String getProductDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Name: ").append(name).append("\n");
        details.append("Price: $").append(String.format("%.2f", price)).append("\n");
        // Check and include other product details if available
        if (description != null && !description.isEmpty()) {
            details.append("Description: ").append(description).append("\n");
        } else {
            details.append("Description: Information not available\n");
        }
        if (imageURL != null && !imageURL.isEmpty()) {
            details.append("Image URL: ").append(imageURL).append("\n");
        } else {
            details.append("Image URL: Information not available\n");
        }
        details.append("Quantity: ").append(quantity).append(" left in stock\n");
        return details.toString();
    }
    static {
        initializeProducts();
    }
    public static List<Product> getProducts() {
        return new ArrayList<>(products); // Return a copy to avoid modification of the original list
    }
    // Static method to create a list of products
    public static void initializeProducts() {
        products.add(new Product(101, "Laptop", 999.99, 10, "https://m.media-amazon.com/images/I/81zKcC5wJ6L._AC_UF1000,1000_QL80_.jpg", "A high-performance laptop."));
        products.add(new Product(102, "Smartphone", 499.99, 15, "https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-15-pro-finish-select-202309-6-7inch-naturaltitanium?wid=5120&hei=2880&fmt=p-jpg&qlt=80&.v=1692845702708", "An innovative smartphone with the best camera."));
        products.add(new Product(103, "DashCam", 99.99, 30, "https://m.media-amazon.com/images/I/61-ouW+YFlL.jpg", "A Dashcam for your car."));
        products.add(new Product(3, "Bluetooth Headphones", 89.99, 25, "https://m.media-amazon.com/images/I/61PAHKjnjCL.jpg", "Bluetooth headphones with noise cancellation."));
        products.add(new Product(4, "Electric Toothbrush", 39.99, 40, "https://m.media-amazon.com/images/I/71Ipo1ZbMFL._AC_UF1000,1000_QL80_.jpg", "Rechargeable toothbrush with smart timer."));
        products.add(new Product(5, "Gaming Laptop", 1299.99, 30, "https://m.media-amazon.com/images/I/712g5R0vkbL._AC_UF894,1000_QL80_.jpg", "The latest gaming laptop with 24GB Ram and 1TB SSD."));
        products.add(new Product(6, "Smart Watch", 199.99, 20, "https://m.media-amazon.com/images/I/71LfnkRgZ4L._AC_UF894,1000_QL80_.jpg", "A smartwatch with health and fitness tracking."));
        products.add(new Product(7, "Tablet", 329.99, 15, "https://m.media-amazon.com/images/I/61goypdjAYL._AC_UF1000,1000_QL80_.jpg", "A versatile tablet perfect for work and play."));
        products.add(new Product(8, "Bluetooth Mouse", 24.99, 50, "https://m.media-amazon.com/images/I/61Mk3YqYHpL.jpg", "A comfortable mouse for everyday use."));
        products.add(new Product(9, "E-Reader", 129.99, 30, "https://media.wired.com/photos/648ba6dff2de86183cf5b4d7/191:100/w_2580,c_limit/Kobo-Libra-2-Gear.jpg", "An e-reader with a paper-like display."));
        products.add(new Product(11, "Portable Speaker", 59.99, 35, "https://m.media-amazon.com/images/I/51kQntfQvPL._AC_UF894,1000_QL80_.jpg", "A portable speaker with excellent sound quality."));
        products.add(new Product(12, "Fitness Tracker", 49.99, 45, "https://m.media-amazon.com/images/I/51JNsCR32BL._AC_UF1000,1000_QL80_.jpg", "Track your daily activity and sleep."));
        products.add(new Product(13, "Espresso Machine", 249.99, 15, "https://cb.scene7.com/is/image/Crate/BrevilleBrstExEsprsAVSSS21_VND/$web_pdp_main_carousel_low$/210409125354/breville-barista-express-espresso-machine.jpg", "Brew cafe-quality espresso at home with ease."));
        products.add(new Product(14, "Yoga Mat", 19.99, 30, "https://images.lululemon.com/is/image/lululemon/LU9AKES_054348_4", "Eco-friendly yoga mat with non-slip surface."));
        products.add(new Product(15, "LED Desk Lamp", 45.99, 20, "https://m.media-amazon.com/images/I/519PNsCh2OL.jpg", "Adjustable desk lamp with multiple brightness settings."));
        products.add(new Product(17, "Cookware Set", 129.99, 25, "https://www.lecreuset.com/dw/image/v2/BDRT_PRD/on/demandware.static/-/Sites-le-creuset-master/default/dw21ea7695/images/cat_cookware_sets/ECOM1901-new-g1.jpg?sw=650&sh=650&sm=fit", "Stainless steel cookware set for all your cooking needs."));
        products.add(new Product(18, "Sunglasses", 89.99, 50, "https://ampere.shop/cdn/shop/files/Dusk-Blackframewithdarktint_polarizedlenses_969c55e5-54b3-44bc-ad49-3c0eac2e49f5_1100x.jpg?v=1700533974", "Stylish sunglasses with UV protection."));
        products.add(new Product(19, "Backpack", 59.99, 35, "https://m.media-amazon.com/images/I/81d1YjW6z-L._AC_UY1000_.jpg", "Durable backpack for travel and everyday use."));
        products.add(new Product(20, "Leather Wallet", 49.99, 45, "https://media.gucci.com/style/DarkGray_Center_0_0_490x490/1463502620/428726_DJ20T_1000_001_080_0000_Light.jpg", "Elegant leather wallet with RFID blocking."));
        products.add(new Product(22, "Noise-cancelling Earplugs", 25.99, 50, "https://m.media-amazon.com/images/I/615FjNyNApL.jpg", "Reusable earplugs with superior noise cancellation."));
    }
    public static void addProduct(Product product) {
        products.add(product);
    }


    @Override
    public String toString() {
        return
                "productID: " + productID +
                        ", name: '" + name + '\'' +
                        ", description: '" + description + '\'' +
                        ", price: $" + price +
                        ", quantity: " + quantity ;
    }
}