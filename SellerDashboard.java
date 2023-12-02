import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton btnUpdateInventory;
    private JButton btnAddNewItem;
    private JLabel titleLabel;

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
        btnInventory = new JButton("Manage Inventory");
        btnUpdateInventory = new JButton("Update Inventory");
        btnAddNewItem = new JButton("Add New Item");

        buttonPanel.add(btnTotalSales);
        buttonPanel.add(btnInventory);
        buttonPanel.add(btnUpdateInventory);
        buttonPanel.add(btnAddNewItem);

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Center align the buttons
        btnTotalSales.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnInventory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUpdateInventory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddNewItem.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the button panel with alignment and padding
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnTotalSales);
        buttonPanel.add(btnInventory);
        buttonPanel.add(btnUpdateInventory);
        buttonPanel.add(btnAddNewItem);
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

    public JButton getBtnUpdateInventory() {
        return btnUpdateInventory;
    }

    public JButton getBtnAddNewItem() {
        return btnAddNewItem;
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

        view.getBtnUpdateInventory().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateInventory();
            }
        });

        view.getBtnAddNewItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddNewItem();
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
        // Logic for inventory
    }

    private void handleUpdateInventory() {
        // Logic for updating inventory
    }

    private void handleAddNewItem() {
        // Logic for adding new item
    }

    // Add a method to display the SellerDashboard
    public void displaySellerDashboard() {
        view.display();
    }
}


// Main class to run the application
class Main {
    public static void main(String[] args) {
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
