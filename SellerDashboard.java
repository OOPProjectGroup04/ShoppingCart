import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

// Model
class SellerModel {
    private int totalSales;
    private int inventoryCount;

    public int getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}

// View 
class SellerView {
    private JFrame frame;
    private JButton btnTotalSales;
    private JButton btnInventory;
    private JButton btnAddNewItem;
    private JLabel titleLabel;
    private JButton btnAddNewInventory;

    public SellerView() {
        frame = new JFrame("Seller Dashboard");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        titleLabel = new JLabel("Seller Dashboard", JLabel.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        btnTotalSales = new JButton("Check Total Sales");
        btnInventory = new JButton("View/Edit Inventory");
        btnAddNewItem = new JButton("Add New Item");
        btnAddNewInventory = new JButton("Add New Inventory");


        buttonPanel.add(btnTotalSales);
        buttonPanel.add(btnInventory);
        buttonPanel.add(btnAddNewItem);
        buttonPanel.add(btnAddNewInventory);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Center align the buttons
        btnTotalSales.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnInventory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddNewItem.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddNewInventory.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the button panel with alignment and padding
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnTotalSales);
        buttonPanel.add(btnInventory);
        buttonPanel.add(btnAddNewItem);
        buttonPanel.add(btnAddNewInventory);
        buttonPanel.add(Box.createVerticalGlue());

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void display() {
        frame.setVisible(true);
    }

    public JButton getBtnTotalSales() {
        return btnTotalSales;
    }

    public JButton getBtnInventory() {
        return btnInventory;
    }

    public JButton getBtnAddNewItem() {
        return btnAddNewItem;
    }

    public JButton getBtnAddNewInventory() {
        return btnAddNewInventory;
    }
}

// InventoryModel
class InventoryModel {
    private List<Product> products;

    public InventoryModel(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isInventoryEmpty() {return products.isEmpty();}

}

// InventoryView
class InventoryView {
    private JFrame frame;
    private JTextArea inventoryTextArea;
    private JButton btnBack;
    private JButton btnEdit;

    public InventoryView(List<Product> products) {
        frame = new JFrame("View/Edit Inventory");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        if (products.isEmpty()) {
            showMessage("No products are currently available in the inventory.");
            return; // Don't create other components if the inventory is empty
        }

        inventoryTextArea = new JTextArea();
        inventoryTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(inventoryTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        btnBack = new JButton("Back to Seller Page");
        frame.add(btnBack, BorderLayout.SOUTH);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button click
                frame.dispose(); // Close the inventory view
            }
        });

        btnEdit = new JButton("Edit Selected Item");
        frame.add(btnEdit, BorderLayout.SOUTH);

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit button click
                handleEdit();
            }
        });
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextArea getInventoryTextArea() {
        return inventoryTextArea;
    }

    public void display() {
        frame.setVisible(true);
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public void showEmptyInventoryMessage() {
        showMessage("No products are currently available in the inventory.");
    }

    public void showDataRetrievalError() {
        showMessage("Error fetching inventory data. Please try again later.");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Inventory Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleEdit() {
    }

    public void updateInventoryText(List<Product> products) {
        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append(String.format("%-10s %-20s %-15s %-10s\n", "Product ID", "Product Name", "Quantity", "Price"));

        for (Product product : products) {
            inventoryText.append(String.format("%-10d %-20s %-15d $%-10.2f\n",
                    product.getProductID(), product.getName(), product.getQuantity(), product.getPrice()));
        }

        inventoryTextArea.setText(inventoryText.toString());
    }

    public boolean showDuplicateProductConfirmation() {
        int option = JOptionPane.showConfirmDialog(frame,
                "A product with the same ID already exists. Do you want to update the existing product?",
                "Duplicate Product Entry", JOptionPane.YES_NO_OPTION);

        return option == JOptionPane.YES_OPTION;
    }
}

// InventoryController
class InventoryController {
    private InventoryModel model;
    private InventoryView view;

    public InventoryController(InventoryModel model, InventoryView view) {
        this.model = model;
        this.view = view;

        view.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit button click
                handleEdit();
            }
        });

        if (model.isInventoryEmpty()) {
            view.showEmptyInventoryMessage();
            return; // Stop further initialization if the inventory is empty
        }

        // Update the inventory text when the view is created
        view.updateInventoryText(model.getProducts());

        // Add a listener to handle the selection of items in the JTextArea
        view.getInventoryTextArea().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleSelection();
            }
        });
    }

    public void handleDataRetrievalError() {
        view.showDataRetrievalError();
    }


    private void handleEdit() {
        // Check if an item is selected
        String selectedText = view.getInventoryTextArea().getSelectedText();
        if (selectedText == null || selectedText.isEmpty()) {
            JOptionPane.showMessageDialog(view.getFrame(), "Please select an item to edit.",
                    "Edit Item", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve the selected item based on the selected text
        String[] lines = view.getInventoryTextArea().getText().split("\n");

        // Find the index of the selected text in the lines
        int selectedRow = -1;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(selectedText)) {
                selectedRow = i;
                break;
            }
        }

        // Check if selectedRow is valid
        if (selectedRow >= 0 && selectedRow < lines.length) {
            String selectedLine = lines[selectedRow];
            int selectedProductId = Integer.parseInt(selectedLine.split("\\s+")[0]);

            // Find the corresponding product based on the selected product ID
            Optional<Product> selectedProduct = model.getProducts().stream()
                    .filter(product -> product.getProductID() == selectedProductId)
                    .findFirst();

            if (selectedProduct.isPresent()) {
                // Create a dialog for editing
                JDialog editDialog = new JDialog(view.getFrame(), "Edit Product", true);
                editDialog.setSize(400, 300);
                editDialog.setLayout(new GridLayout(6, 2));

                // Create labels and text fields for each property
                JLabel idLabel = new JLabel("Product ID:");
                JTextField idField = new JTextField(String.valueOf(selectedProduct.get().getProductID()));
                JLabel nameLabel = new JLabel("Product Name:");
                JTextField nameField = new JTextField(selectedProduct.get().getName());
                JLabel descriptionLabel = new JLabel("Description:");
                JTextField descriptionField = new JTextField(selectedProduct.get().getDescription());
                JLabel priceLabel = new JLabel("Price:");
                JTextField priceField = new JTextField(String.valueOf(selectedProduct.get().getPrice()));
                JLabel quantityLabel = new JLabel("Quantity:");
                JTextField quantityField = new JTextField(String.valueOf(selectedProduct.get().getQuantity()));

                // Create buttons for saving changes and canceling
                JButton saveButton = new JButton("Save Changes");
                JButton cancelButton = new JButton("Cancel");

                // Add action listener to the save button
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Validate non-negative quantity and price
                        if (validateNonNegativeValues(quantityField, priceField)) {
                            // Update the selected product with the new values
                            selectedProduct.get().setProductID(Integer.parseInt(idField.getText()));
                            selectedProduct.get().setName(nameField.getText());
                            selectedProduct.get().setDescription(descriptionField.getText());
                            selectedProduct.get().setPrice(Double.parseDouble(priceField.getText()));
                            selectedProduct.get().setQuantity(Integer.parseInt(quantityField.getText()));

                            // Update the product in the list
                            updateProductInList(selectedProductId, selectedProduct.get());

                            // Update the display in the inventory view
                            view.updateInventoryText(model.getProducts());

                            // Close the dialog
                            editDialog.dispose();
                        }
                    }
                });

                // Add action listener to the cancel button
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Close the dialog without saving changes
                        editDialog.dispose();
                    }
                });

                // Add components to the dialog
                editDialog.add(idLabel);
                editDialog.add(idField);
                editDialog.add(nameLabel);
                editDialog.add(nameField);
                editDialog.add(descriptionLabel);
                editDialog.add(descriptionField);
                editDialog.add(priceLabel);
                editDialog.add(priceField);
                editDialog.add(quantityLabel);
                editDialog.add(quantityField);
                editDialog.add(saveButton);
                editDialog.add(cancelButton);

                // Set the layout and make the dialog visible
                editDialog.setLayout(new GridLayout(6, 2));
                editDialog.setVisible(true);
            }
        } else {
            // Handle the case where selectedRow is invalid
            JOptionPane.showMessageDialog(view.getFrame(), "Invalid selection. Please try again.",
                    "Edit Item", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method to validate non-negative quantity and price
    private boolean validateNonNegativeValues(JTextField quantityField, JTextField priceField) {
        try {
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            if (quantity < 0 || price < 0) {
                JOptionPane.showMessageDialog(view.getFrame(), "Please enter non-negative values for quantity and price.",
                        "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view.getFrame(), "Please enter valid numbers for quantity and price.",
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Method to update the product in the model's list based on the old product ID
    private void updateProductInList(int oldProductId, Product updatedProduct) {
        List<Product> productList = model.getProducts();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductID() == oldProductId) {
                productList.set(i, updatedProduct);
                break;
            }
        }
    }

    // Add a method to check for duplicate product ID in the inventory
    private boolean checkForDuplicateProductId(int editedProductId, int originalProductId) {
        // Check if a product with the same ID already exists, excluding the original product ID
        boolean isDuplicate = model.getProducts().stream()
                .anyMatch(product -> product.getProductID() == editedProductId && product.getProductID() != originalProductId);

        if (isDuplicate) {
            JOptionPane.showMessageDialog(view.getFrame(), "A product with the same ID already exists. Please choose a different ID.",
                    "Duplicate Product ID", JOptionPane.ERROR_MESSAGE);
        }

        return isDuplicate;
    }


    // Add a method to handle the selection of items in the JTextArea
    private void handleSelection() {
        // You can add logic to handle selection if needed
    }

    private void handleEditProduct(Product product) {
        // Update the display in the inventory view
        view.updateInventoryText(model.getProducts());
    }
}

// SellerController (Controller)
class SellerController {
    private SellerModel model;
    private SellerView view;

    public SellerController(SellerModel model, SellerView view) {
        this.model = model;
        this.view = view;

        view.getBtnTotalSales().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTotalSales();
            }
        });

        view.getBtnInventory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleInventory();
            }
        });


        view.getBtnAddNewItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddNewItem();
            }
        });

        view.getBtnAddNewInventory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddNewInventory();
            }
        });

        // Initialize model data (for demonstration purposes)
        model.setTotalSales(500);
        model.setInventoryCount(50);
    }

    private void handleTotalSales() {
        // Logic for total sales
        // Retrieve data from the model
        int totalSales = model.getTotalSales();
        int inventoryCount = model.getInventoryCount();

        // Calculate other metrics
        double netSales = calculateNetSales(totalSales);
        double shippingCosts = calculateShippingCosts();
        double productionCosts = calculateProductionCosts();
        double sellerFees = calculateSellerFees();
        double profit = calculateProfit(netSales, shippingCosts, productionCosts, sellerFees);

        // Display the sales metrics in a dialog
        String message = "Total Sales: " + totalSales + "\n" +
                "Net Sales: $" + netSales + "\n" +
                "Shipping Costs: $" + shippingCosts + "\n" +
                "Production Costs: $" + productionCosts + "\n" +
                "Seller Fees: $" + sellerFees + "\n" +
                "Profit: $" + profit;

        JOptionPane.showMessageDialog(view.getFrame(), message, "Total Sales Information", JOptionPane.INFORMATION_MESSAGE);
    }


    // Add logic for calculating the sales metrics
    private double calculateNetSales(int totalSales) {
        return totalSales * 10; // For demonstration purposes
    }

    private double calculateShippingCosts() {
        // Add logic
        return 200.0; // For demonstration purposes
    }

    private double calculateProductionCosts() {
        // Add logic
        return 3000.0; // For demonstration purposes
    }

    private double calculateSellerFees() {
        // Add logic
        return 500.0; // For demonstration purposes
    }

    private double calculateProfit(double netSales, double shippingCosts, double productionCosts, double sellerFees) {
        // Add logic
        return netSales - (shippingCosts + productionCosts + sellerFees);
    }

    private void handleInventory() {
        // Get or initialize your list of products
        List<Product> products = Product.getProducts();

        if (products.isEmpty()) {
            // Use the InventoryView to show the empty inventory message
            InventoryView inventoryView = new InventoryView(products);
            inventoryView.showEmptyInventoryMessage();
            return; // Stop further initialization if the inventory is empty
        }

        // Display the inventory view
        InventoryModel inventoryModel = new InventoryModel(products);
        InventoryView inventoryView = new InventoryView(products);
        InventoryController inventoryController = new InventoryController(inventoryModel, inventoryView);

        // Add logic to update the inventoryTextArea in the view
        inventoryView.updateInventoryText(products);
        inventoryView.display();
    }


    private void handleAddNewItem() {
        new AddNewItem().setVisible(true);
    }

    // Add a method to display the SellerDashboard
    public void displaySellerDashboard() {view.display();}


    private void handleAddNewInventory() {
        new AddInventory().setVisible(true);
    }


}


// Main class to run the application
class Main {
    public static void main(String[] args) {
        List<Product> products = Product.getProducts();

        if (products.isEmpty()) {
            // Handle empty inventory condition if needed
            System.out.println("Empty inventory. Handle accordingly.");
            return;
        }

        SellerModel model = new SellerModel();
        SellerView view = new SellerView();
        SellerController controller = new SellerController(model, view);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                view.display();
            }
        });
    }
}

