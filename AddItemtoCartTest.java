
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AddItemtoCartTest {

    private CatalogView catalogView;
     Product sampleProduct;

    int shoppingCartSize =0;

    @Before
    public void setUp() {
        // Initialize CatalogView
        catalogView = new CatalogView();
        ArrayList<Object> shoppingCart = new ArrayList<>(); // Initialize the shopping cart list
        // Sample product to be used in the tests
        sampleProduct = new Product(1010101, "Test", 99.99, 10, "https://m.media-amazon.com/images/I/81zKcC5wJ6L._AC_UF1000,1000_QL80_.jpg", "Sample.");
    }

    @Test
    public void whenProductAddedToCart_thenCartSizeShouldIncrease() {
        catalogView.addToCart(sampleProduct);

        assertEquals("Cart size should increase by 1", 1, catalogView.getCartSize());


    }


}
