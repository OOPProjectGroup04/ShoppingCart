import java.util.ArrayList;
import java.util.List;


public class ShoppingCart{
    
    List<Product> items = new ArrayList<Product>(); 
    double totalAmount = 0.0;

    public void addItem(Product product, int quantity){
        // Adding item to cart
        items.add(product);
        totalAmount += product.getPrice() * quantity;
}

}