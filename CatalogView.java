import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * This class represents the main view of the product catalog in a GUI application.
 * It displays products and allows users to add items to a shopping cart.
 * @author Freddy Ingle
 * @author George Martinez
 */
class CatalogView {
    private JFrame frame;
    private JPanel productPanel;
    private static JLabel cartItemCountLabel; //keep count of cart items
    private static List<Product> shoppingCart; //list of cart items


    /**
     * Gets the current size of the shopping cart.
     * 
     * @return the number of items in the shopping cart.
     */
    public int getCartSize() { //use for junit test
        return shoppingCart.size();
    }

    /**
     * Constructs the CatalogView by initializing the GUI components.
     */
    public CatalogView() {
        frame = new JFrame("Product Catalog");
        productPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, auto rows, 10px gaps

        // Cart Panel at the top-right
        JPanel cartPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cartItemCountLabel = new JLabel("Cart: 0 items"); // Initialize the label with 0 items
        cartPanel.add(cartItemCountLabel);
        // "View Cart" button
        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(e -> viewCart());

        // Add the label and button to the cart panel
        cartPanel.add(cartItemCountLabel);
        cartPanel.add(viewCartButton);
        frame.add(cartPanel, BorderLayout.NORTH); // Add the cart panel to the frame


        JScrollPane scrollPane = new JScrollPane(productPanel);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        shoppingCart = new ArrayList<>(); // Initialize the shopping cart list
        refreshCartDisplay();
    }


    /**
     * Sets the visibility of the main frame.
     * 
     * @param visible boolean flag to set the frame's visibility.
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

      /**
     * Sets and displays the list of products in the catalog.
     * 
     * @param products the list of products to be displayed.
     */
    public void setProducts(List<Product> products) {
        productPanel.removeAll(); // Remove all previous components
        for (Product product : products) {
            JPanel card = createProductCard(product);
            productPanel.add(card);
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

    /**
     * Creates and returns a JPanel representing a single product card.
     * 
     * @param product the product to create a card for.
     * @return a JPanel representing the product.
     */
    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()), SwingConstants.CENTER);

        // Adjust the description label to wrap text
        JLabel descriptionLabel = new JLabel("<html><body style='width: 100px'>" + product.getDescription() + "</body></html>");

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnAddToCart = new JButton("Add to Cart");
        JButton btnViewDetails = new JButton("View Details");

        btnAddToCart.addActionListener(e -> addToCart(product));
        btnViewDetails.addActionListener(e -> viewProductDetails(product));

        buttonsPanel.add(btnAddToCart);
        buttonsPanel.add(btnViewDetails);

        // Use a SwingWorker to load image in the background
        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                URL imageUrl = new URL(product.getImageURL());
                ImageIcon imageIcon = new ImageIcon(imageUrl);
                Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                return new ImageIcon(image);
            }

            @Override
            protected void done() {
                try {
                    JLabel imageLabel = new JLabel(get());
                    card.add(imageLabel, BorderLayout.WEST);
                    card.validate();
                    card.repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the error here
                }
            }
        }.execute();

        card.add(nameLabel, BorderLayout.NORTH);
        card.add(priceLabel, BorderLayout.SOUTH);
        card.add(descriptionLabel, BorderLayout.CENTER);
        card.add(buttonsPanel, BorderLayout.PAGE_END);

        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        return card;
    }


    /**
     * Adds a given product to the shopping cart and refreshes the cart display.
     * 
     * @param product the product to add to the shopping cart.
     */
    public void addToCart(Product product) {
        shoppingCart.add(product);
        refreshCartDisplay();
        JOptionPane.showMessageDialog(frame, product.getName() + " added to cart!");
    }
    /**
     * Clears all items from the shopping cart and updates the display.
     */
    public void clearShoppingCart() {
        shoppingCart.clear();
        refreshCartDisplay();
    }


      /**
     * Displays details of a specific product in a dialog.
     * 
     * @param product the product whose details are to be displayed.
     */
    private void viewProductDetails(Product product) {
        // Implementation of product details view
        if (product.isAvailable()) {
            String productDetails = product.getProductDetails();
            JOptionPane.showMessageDialog(frame, "Product Details:\n" + productDetails);
        } else {
            JOptionPane.showMessageDialog(frame, "Product is out of stock or unavailable.");
        }
    }

     /**
     * Displays the shopping cart view.
     */
    private void viewCart() {
        // Implementation to view the cart contents
                refreshCartDisplay();

        ShoppingCartView cartView = new ShoppingCartView(shoppingCart);
        cartView.displayCartItems();
    }

    /**
     * Displays the customer homepage view.
     */
    public void displayCustomerHomepage() {
        refreshCartDisplay();
        SwingUtilities.invokeLater(() -> {
            CatalogMain catalogMain = new CatalogMain();
            catalogMain.display();
        });
    }

     /**
     * Updates the cart display label with the current number of items in the cart.
     */
    public static void refreshCartDisplay() {

        cartItemCountLabel.setText("Cart: " + shoppingCart.size() + " items");
        // Any other UI updates related to the cart
    }





}
/**
 * Controller for managing the product catalog view and interactions.
 */
class ProductCatalogController {
    private List<Product> products;
    private CatalogView view;

     /**
     * Constructor for the product catalog controller.
     * 
     * @param view the CatalogView to control.
     */
    public ProductCatalogController(CatalogView view) {
        
        this.view = view;
        this.products = Product.getProducts();
        initController();
    }

    /**
     * Initializes the controller by setting up the view with data.
     */
    private void initController() {

        updateView();
    }


    /**
     * Updates the view with the list of products.
     */
    private void updateView() {
        view.setProducts(products);
    }

    // methods for handling user actions
}
/**
 * Main class for launching the catalog view as part of a GUI application.
 */// CatalogMain (Main class for customer homepage)
class CatalogMain {
    private CatalogView view;

    /**
     * Constructs the main application view and controller.
     */
    public CatalogMain() {
        this.view = new CatalogView();
        new ProductCatalogController(view);
    }

     /**
     * Displays the main application view.
     */
    public void display() {
        view.setVisible(true);
    }
}

