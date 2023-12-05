import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AddInventory extends JFrame {
    private JTextField productIdField, quantityField;
    private JButton submitButton;

    public AddInventory() {
        createUI();
    }
    private void createUI() {
        setTitle("Add New Inventory");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Product ID:"));
        productIdField = new JTextField(10);
        add(productIdField);

        JLabel itemName = new JLabel("Product Name:");
        add(itemName);

        JLabel label = new JLabel("Current Quantity:");
        add(label);


        add(new JLabel("Add Quantity:"));
                quantityField = new JTextField();
                add(quantityField);



                productIdField.addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusLost(FocusEvent e) {
                        try {
                            int productId = Integer.parseInt(productIdField.getText());
                            Product product = Product.getProduct(productId);
                            if (product != null) {
                                label.setText("Current Quantity: " + product.getQuantity());
                                itemName.setText("Product Name: " + product.getName());
                            } else {
                                label.setText("Current Quantity: Not found");
                                itemName.setText("Product Name: Not found");
                            }
                        } catch (NumberFormatException ex) {
                            label.setText("Current Quantity: Invalid ID");
                            itemName.setText("Product Name: Invalid ID");
                        }
                    }
                });

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

    private void updateInventory() {
        try {
            int productId = Integer.parseInt(productIdField.getText());
            int quantityToAdd = Integer.parseInt(quantityField.getText());
            Product product = Product.getProduct(productId);
    
            if (product != null) {
                product.setQuantity(product.getQuantity() + quantityToAdd);
                JOptionPane.showMessageDialog(this, "Inventory Updated");
            } else {
                JOptionPane.showMessageDialog(this, "Product not found");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

}
