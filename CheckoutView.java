import javax.swing.*;
import java.awt.*;

import java.util.List;

public class CheckoutView {
    private JFrame frame;
    private List<Product> shoppingCart; // The shopping cart items
    private double total; // Total price of items in the cart
    private CatalogView catalogView; // Reference to the CatalogView

    public CheckoutView(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
        this.catalogView = catalogView;
        frame = new JFrame("Checkout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Calculate the total
        total = shoppingCart.stream().mapToDouble(Product::getPrice).sum();

        // Create UI components
        createCartItemsPanel();
        createCheckoutInfoPanel();
        createBottomPanel();

        // Finalize and show the frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createCartItemsPanel() {
        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        shoppingCart.forEach(product -> {
            JPanel productPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel productLabel = new JLabel(product.getName() + " - $" + product.getPrice());
            productPanel.add(productLabel);
            cartItemsPanel.add(productPanel);
        });

        frame.add(new JScrollPane(cartItemsPanel), BorderLayout.CENTER);
    }

    private void createCheckoutInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Add labels and text fields for checkout information
        infoPanel.add(new JLabel("Name on Card:"));
        infoPanel.add(new JTextField());
        infoPanel.add(new JLabel("Credit Card Number:"));
        infoPanel.add(new JTextField());
        infoPanel.add(new JLabel("Expiration Date:"));
        infoPanel.add(new JTextField());
        infoPanel.add(new JLabel("CVV:"));
        infoPanel.add(new JTextField());
        infoPanel.add(new JLabel("Shipping Address:"));
        infoPanel.add(new JTextField());

        frame.add(infoPanel, BorderLayout.NORTH);
    }

    private void createBottomPanel() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel totalLabel = new JLabel("Total: $" + total);
        JButton checkoutButton = new JButton("Complete Purchase");
        JButton backButton = new JButton("Back to Cart");

        checkoutButton.addActionListener(e -> completePurchase());
        backButton.addActionListener(e -> goBackToCart());

        bottomPanel.add(backButton);
        bottomPanel.add(totalLabel);
        bottomPanel.add(checkoutButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void completePurchase() {
        JOptionPane.showMessageDialog(frame, "Thank you for your purchase!");
        shoppingCart.clear();
        frame.dispose();
    }

    private void goBackToCart() {
        // Placeholder for going back to the cart
        frame.dispose();
        // Potentially re-open the cart view or go back to the main shopping view
    }

}
