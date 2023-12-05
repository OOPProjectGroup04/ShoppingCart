import org.junit.Test;
import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void productCreation_withValidDetails_shouldSetCorrectAttributes() {
        // Create a product with the full constructor details
        Product product = new Product(1010101, "Test", 99.99, 10, 
                                      "https://m.media-amazon.com/images/I/81zKcC5wJ6L._AC_UF1000,1000_QL80_.jpg", 
                                      "Sample.");

        // Assert that each attribute is set correctly
        assertEquals("Product ID should be 1010101", 1010101, product.getProductID());
        assertEquals("Product name should be 'Test'", "Test", product.getName());
        assertEquals("Product price should be 99.99", 99.99, product.getPrice(), 0.001);
        assertEquals("Product quantity should be 10", 10, product.getQuantity());
        assertEquals("Product imageURL should match", 
                     "https://m.media-amazon.com/images/I/81zKcC5wJ6L._AC_UF1000,1000_QL80_.jpg", 
                     product.getImageURL());
        assertEquals("Product description should be 'Sample.'", "Sample.", product.getDescription());
    }
    
}



