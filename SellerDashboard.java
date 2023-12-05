import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

/**
 * This class represents the model for a seller in the application.
 * It handles the storage and management of the seller's total sales and inventory count.
 * 
 * Authors are written for each class in this file
 * @author freddy ingle
 * @author george martinez
 */
// Model
class SellerModel {
    private int totalSales;
    private int inventoryCount;

    /**
     * Gets the total sales of the seller.
     *
     * @return The total sales amount.
     */
    public int getTotalSales() {
        return totalSales;
    }

    /**
     *  set total sales
     * @param totalSales
     */
    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }

    /**
     * 
     * @return inventoryCount
     */
    public int getInventoryCount() {
        return inventoryCount;
    }

    /**
     *  set inventory count
     * @param inventoryCount
     */
    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }
}

/**
 * This class represents the view for a seller in the application.
 * It creates and displays the seller dashboard interface including buttons for various actions.
 * @author george martinez
 * @author freddy ingle
 * @author Bryan Cooke
 */
// View 
class SellerView {
    private JFrame frame;
    private JButton btnTotalSales;
    private JButton btnInventory;
    private JButton btnAddNewItem;
    private JLabel titleLabel;
    private JButton btnAddNewInventory;

    /**
     * Constructs the SellerView with all its UI components.
     */
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
        //btnAddNewInventory = new JButton("Add New Inventory");


        buttonPanel.add(btnTotalSales);
        buttonPanel.add(btnInventory);
        buttonPanel.add(btnAddNewItem);
        //buttonPanel.add(btnAddNewInventory);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Center align the buttons
        btnTotalSales.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnInventory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddNewItem.setAlignmentX(Component.CENTER_ALIGNMENT);
       // btnAddNewInventory.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the button panel with alignment and padding
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnTotalSales);
        buttonPanel.add(btnInventory);
        buttonPanel.add(btnAddNewItem);
        //buttonPanel.add(btnAddNewInventory);
        buttonPanel.add(Box.createVerticalGlue());

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * 
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * set the frame
     */
    public void display() {
        frame.setVisible(true);
    }

    /**
     * 
     * @return total sales button
     */
    public JButton getBtnTotalSales() {
        return btnTotalSales;
    }

    /**
     * 
     * @return inventory button
     */
    public JButton getBtnInventory() {
        return btnInventory;
    }

    /**
     * 
     * @return add new item button
     */
    public JButton getBtnAddNewItem() {
        return btnAddNewItem;
    }

    /*public JButton getBtnAddNewInventory() {
        return btnAddNewInventory;
    }*/
}

// InventoryModel
/**
 * Model in MVC pattern for inventory 
 * @author freddy ingle
 * @author george martinez
 */
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
/**
 * View in MVC pattern for inventory 
 * It creates and displays the interface for viewing and editing the inventory.
 * @author george martinez
 * @author freddy ingle
 */
class InventoryView {
    private JFrame frame;
    private JTextArea inventoryTextArea;
    private JButton btnBack;
    private JButton btnEdit;

    /**
     * Constructs the InventoryView with the specified list of products.
     *
     * @param products The list of products to display in the inventory view.
     */
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

    /**
     * 
     * @return frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * 
     * @return inventory text area
     */
    public JTextArea getInventoryTextArea() {
        return inventoryTextArea;
    }

    /**
     * set the frame
     */
    public void display() {
        frame.setVisible(true);
    }

    /**
     * 
     * @return Edit button
     */
    public JButton getBtnEdit() {
        return btnEdit;
    }

    /**
     * show no product in inventory message
     */
    public void showEmptyInventoryMessage() {
        showMessage("No products are currently available in the inventory.");
    }

    /**
     * show error message : data retrieval error
     */
    public void showDataRetrievalError() {
        showMessage("Error fetching inventory data. Please try again later.");
    }

    /**
     * 
     * @param message
     */
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Inventory Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleEdit() {
    }

    /**
     * edits the selected inventory with new user changes in string format
     * @param products
     */
    public void updateInventoryText(List<Product> products) {
        StringBuilder inventoryText = new StringBuilder();
        inventoryText.append(String.format("%-10s %-20s %-15s %-10s\n", "Product ID", "Product Name", "Quantity", "Price"));

        for (Product product : products) {
            inventoryText.append(String.format("%-10d %-20s %-15d $%-10.2f\n",
                    product.getProductID(), product.getName(), product.getQuantity(), product.getPrice()));
        }

        inventoryTextArea.setText(inventoryText.toString());
    }

    /**
     * Shows a confirmation dialog for duplicate product entries.
     * @return true if the user chooses to update the existing product, false otherwise.
     */
    public boolean showDuplicateProductConfirmation() {
        int option = JOptionPane.showConfirmDialog(frame,
                "A product with the same ID already exists. Do you want to update the existing product?",
                "Duplicate Product Entry", JOptionPane.YES_NO_OPTION);

        return option == JOptionPane.YES_OPTION;
    }
}

// InventoryController
/**
 * This class serves as the controller for the InventoryView.
 * It handles user interactions from the InventoryView and updates the InventoryModel accordingly.
 * @author george martinez
 */
class InventoryController {
    private InventoryModel model;
    private InventoryView view;

    /**
     * Constructs an InventoryController with the specified model and view.
     *
     * @param model The InventoryModel to manage inventory data.
     * @param view  The InventoryView to interact with the user.
     */
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

     /**
     * Handles errors during data retrieval by showing an error message.
     */
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
                        // Check for duplicate product ID in the inventory
                        int editedProductId = Integer.parseInt(idField.getText());
                        if (!checkForDuplicateProductId(editedProductId, selectedProductId)) {
                            // Update the selected product with the new values
                            selectedProduct.get().setProductID(editedProductId);
                            selectedProduct.get().setName(nameField.getText());
                            selectedProduct.get().setDescription(descriptionField.getText());
                            selectedProduct.get().setPrice(Double.parseDouble(priceField.getText()));
                            selectedProduct.get().setQuantity(Integer.parseInt(quantityField.getText()));

                            // Update the product in the list
                            updateProductInList(selectedProductId, selectedProduct.get());

                            // Update the display in the inventory view
                            view.updateInventoryText(model.getProducts());
                        }

                        // Close the dialog
                        editDialog.dispose();
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

    
     /**
     * Updates a product in the model's product list.
     *
     * @param oldProductId   The old product ID.
     * @param updatedProduct The updated product object.
     */
    private void updateProductInList(int oldProductId, Product updatedProduct) {
        List<Product> productList = model.getProducts();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductID() == oldProductId) {
                productList.set(i, updatedProduct);
                break;
            }
        }
    }
    
    
    /**
     * Checks for duplicate product ID in the inventory.
     *
     * @param editedProductId   The new product ID.
     * @param originalProductId The original product ID.
     * @return true if a duplicate product ID exists, false otherwise.
     */
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

    /**
     * Handles updating the view after editing a product.
     *
     * @param product The edited product.
     */
    private void handleEditProduct(Product product) {
        // Update the display in the inventory view
        view.updateInventoryText(model.getProducts());
    }
}

// SellerController (Controller)
/**
 * This class serves as the controller for the SellerView.
 * It handles user interactions from the SellerView and updates the SellerModel accordingly.
 * @author bryan cooke
 * @author george martinez
 * @author freddy ingle
 */
class SellerController {
    private SellerModel model;
    private SellerView view;

     /**
     * Constructs a SellerController with the specified model and view.
     *
     * @param model The SellerModel to manage seller data.
     * @param view  The SellerView to interact with the user.
     */
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

        /*view.getBtnAddNewInventory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddNewInventory();
            }
        });*/

        // Initialize model data (for demonstration purposes)
        model.setTotalSales(500);
        model.setInventoryCount(50);
    }

    /**
     * Handles the display of total sales metrics.
     */
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
    /**
     * Calculates the net sales.
     *
     * @param totalSales The total sales amount.
     * @return The net sales amount.
     */
    private double calculateNetSales(int totalSales) {
        return totalSales * 10; // For demonstration purposes
    }

    /**
     * calculates shipping costs
     * 
     * @return shipping cost amount
     */
    private double calculateShippingCosts() {
        // Add logic
        return 200.0; // For demonstration purposes
    }

    /**
     * 
     * @return production costs amount
     */
    private double calculateProductionCosts() {
        // Add logic
        return 3000.0; // For demonstration purposes
    }

    /**
     * 
     * @return seller fees amount
     */
    private double calculateSellerFees() {
        // Add logic
        return 500.0; // For demonstration purposes
    }

    /**
     * 
     * @param netSales
     * @param shippingCosts
     * @param productionCosts
     * @param sellerFees
     * @return Profit
     */
    private double calculateProfit(double netSales, double shippingCosts, double productionCosts, double sellerFees) {
        // Add logic
        return netSales - (shippingCosts + productionCosts + sellerFees);
    }

    /**
     * Handles the display and management of inventory.
     */
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


    /**
     * display new item 
     */
    private void handleAddNewItem() {
        new AddNewItem().setVisible(true);
    }

    
    /**
     * display the seller Dashboard
     */
    public void displaySellerDashboard() {view.display();}


    /**
     * display the new inventory 
     */
    private void handleAddNewInventory() {
        new AddInventory().setVisible(true);
    }


}



/**
 * The main class for the application. It sets up the seller dashboard by initializing
 * the model, view, and controller, and then makes the view visible.
 * @author freddy ingle
 * @author george martinez
 */
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

