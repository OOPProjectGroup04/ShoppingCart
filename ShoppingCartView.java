import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
class ShoppingCartView {
    private List<Product> shoppingCart;
    private JFrame frame; 
    // Checkout button at the bottom of the dialog
    private JButton checkoutButton = new JButton("Checkout");


    public ShoppingCartView(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
        this.frame = new JFrame("Shopping Cart");
        this.checkoutButton = new JButton("Checkout");

        // Set up the frame and add components...
        checkoutButton.addActionListener(e -> openCheckoutView());
        frame.add(checkoutButton, BorderLayout.SOUTH); // Or wherever it needs to be placed

        // More setup...
        frame.pack();
        frame.setVisible(true);
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
        
        
        cartDialog.add(scrollPane, BorderLayout.CENTER);
        cartDialog.add(checkoutButton, BorderLayout.SOUTH);
        
        cartDialog.setSize(300, 400); 
        cartDialog.setLocationRelativeTo(null); // Center the dialog
        cartDialog.setVisible(true);
    }
    private void openCheckoutView() {
        new CheckoutView(shoppingCart); // Create and display the checkout view
        
        frame.dispose(); // Close or hide the shopping cart window
    }
    

}