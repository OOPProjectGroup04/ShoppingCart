import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class represents a GUI form for adding new items to an inventory.
 * It allows the user to input details about a product, such as its name,
 * quantity, price, ID, image URL, and description. It also handles the 
 * submission of this data to update or add a new product in the inventory.
 * @author freddy ingle
 * @author George martinez
 * @author Bryan Cooke
 */
public class AddNewItem extends JFrame {
    private JTextField productNameField, quantityField, priceField, productIdField, productImageURLField, productDescribeField;
    private JButton submitButton;

    /**
     * Constructor for AddNewItem. It initializes the user interface.
     */
    public AddNewItem() {
        createUI();
    }

    /**
     * Creates the user interface for the AddNewItem window.
     * This method sets up the layout and components of the GUI.
     */
    private void createUI() {
        setTitle("Add New Item");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Product Name:"));
        productNameField = new JTextField(10);
        add(productNameField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField(10);
        add(quantityField);

        add(new JLabel("Price:"));
        priceField = new JTextField("00.00", 10); // Example current price
        add(priceField);

        Random randomNum = new Random();
        int min = 100;
        int max = 1000;
        int randomNumber = randomNum.nextInt(max - min + 1) + min;

        add(new JLabel("Product ID:"));
        productIdField = new JTextField(String.valueOf(randomNumber), 10);
        add(productIdField);

        add(new JLabel("Product Image URL:"));
        productImageURLField = new JTextField(10);
        add(productImageURLField);

        add(new JLabel("Product Description:"));
        productDescribeField = new JTextField(10);
        add(productDescribeField);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateInventory();
            }
        });
        add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton);

        setLocationRelativeTo(null); // Center on screen
    }

    /**
     * Updates the inventory with the information provided in the form.
     * If a product with the same ID already exists, it offers an option to
     * update the existing product. Otherwise, it adds a new product.
     */
    private void updateInventory() {
        try {
            String productName = productNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            int productId = Integer.parseInt(productIdField.getText());
            String productImageURL = productImageURLField.getText(); // Example image URL
            String productDescribe = productDescribeField.getText();
            // Check for negative quantity and price
            if (quantity < 0 || price < 0) {
                JOptionPane.showMessageDialog(this, "Please enter non-negative values for quantity and price.");
                return;  // Exit the method to prevent further processing
            }
            // Check for duplicate product entry
            Product existingProduct = Product.getProduct(productId);
            if (existingProduct != null) {
                int option = JOptionPane.showConfirmDialog(null,
                        "A product with the same ID already exists. Do you want to update the existing product?",
                        "Duplicate Product Entry", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    // Update the existing product
                    existingProduct.setName(productName);
                    existingProduct.setQuantity(quantity);
                    existingProduct.setPrice(price);
                    existingProduct.setImageURL(productImageURL);
                    existingProduct.setDescription(productDescribe);
                    JOptionPane.showMessageDialog(this, "Product updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Product not updated.");
                }
            } else {
                // No duplicate found, add the new product to inventory
                Product.addProduct(new Product(productId, productName, price, quantity, productImageURL, productDescribe));
                JOptionPane.showMessageDialog(this, "Product added successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity, price, and product ID.");
        }
    }
}
