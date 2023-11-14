import java.util.ArrayList;
import java.util.List;


public class ShoppingCart{
    private List<Product> items;
    private double totalAmount;

    public ShoppingCart () {
        // Constructor
         items = new ArrayList<>(); 
         totalAmount = 0.0;
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addItem(Product product, int quantity){
        // Adding item to cart
        items.add(product);
        totalAmount += product.getPrice() * quantity;
}

    public void removeItem(Product product, int quantity) {
        // Removing item from cart
        items.remove(product);
        totalAmount -= product.getPrice() * quantity;
        
    }

}