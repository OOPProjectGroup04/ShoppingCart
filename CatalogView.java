import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class CatalogView {
    private JFrame frame;
    private JPanel productPanel;
    private JLabel cartItemCountLabel; //keep count of cart items
    private List<Product> shoppingCart; //list of cart items

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

    
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void setProducts(List<Product> products) {
        productPanel.removeAll(); // Remove all previous components
        for (Product product : products) {
            JPanel card = createProductCard(product);
            productPanel.add(card);
        }
        productPanel.revalidate();
        productPanel.repaint();
    }

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
    
    private void addToCart(Product product) {
        shoppingCart.add(product);
        updateCartDisplay();
        JOptionPane.showMessageDialog(frame, product.getName() + " added to cart!");
    }
    
    private void updateCartDisplay() {
        cartItemCountLabel.setText("Cart: " + shoppingCart.size() + " items");
    }
    private void viewProductDetails(Product product) {
        // Implementation of product details view
        JOptionPane.showMessageDialog(frame, "Product Details:\n" + product);
    }
    private void viewCart() {
        // Implementation to view the cart contents
        ShoppingCartView cartView = new ShoppingCartView(shoppingCart);
        cartView.displayCartItems();
    }

}
class ShoppingCartView {
    private List<Product> shoppingCart;

    public ShoppingCartView(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

     public void displayCartItems() {
        JDialog cartDialog = new JDialog(); 
        cartDialog.setTitle("Shopping Cart");
        cartDialog.setLayout(new BorderLayout());
        
        StringBuilder sb = new StringBuilder("<html>");
        for (Product product : shoppingCart) {
            sb.append(product.getName()).append(" - $").append(product.getPrice()).append("<br>");
        }
        sb.append("</html>");
        
        JLabel itemsLabel = new JLabel(sb.toString());
        JScrollPane scrollPane = new JScrollPane(itemsLabel); // In case of many items
        
        // Checkout button at the bottom of the dialog
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            // Placeholder for checkout functionality
            JOptionPane.showMessageDialog(cartDialog, "Proceeding to Checkout...");
        });

        cartDialog.add(scrollPane, BorderLayout.CENTER);
        cartDialog.add(checkoutButton, BorderLayout.SOUTH);
        
        cartDialog.setSize(300, 400); 
        cartDialog.setLocationRelativeTo(null); // Center the dialog
        cartDialog.setVisible(true);
    }

}
class ProductCatalogController {
    private List<Product> products;
    private CatalogView view;

    public ProductCatalogController(CatalogView view) {
        this.view = view;
        this.products = Product.getProducts(); 
        initController();
    }

    private void initController() {
        
        updateView();
    }

    private void updateView() {
        view.setProducts(products);
    }

    // Additional methods for handling user actions
}
class CatalogMain {
    public static void main(String[] args) {
        CatalogView view = new CatalogView();
        new ProductCatalogController(view);
        view.setVisible(true);
    }
}
