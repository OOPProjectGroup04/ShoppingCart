 
 import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;

 class ProductModel {
    private List<Product> products;
    private List<ChangeListener> listeners = new ArrayList<>();

    public ProductModel() {
        products = Product.getProducts(); // Load initial data
        
        for (Product product : products) {
            System.out.println(product); // Print product details to the console
            System.out.println("Product: " + product.getName());
            System.out.println("Image URL: " + product.getImageURL());
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        notifyChange();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyChange() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }
}
/* 
 class ProductCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof Product) {
            Product product = (Product) value;

            // Create a JPanel to hold both text and image
            JPanel panel = new JPanel(new BorderLayout());

            // Create a JLabel for displaying product information
            JLabel label = new JLabel("<html><b>Product ID:</b> " + product.getProductID() +
                    "<br><b>Name:</b> " + product.getName() +
                    "<br><b>Price:</b> $" + product.getPrice() + "</html>");

            // Create an ImageIcon using the provided image URL
            ImageIcon imageIcon = new ImageIcon(product.getImageURL());
            JLabel imageLabel = new JLabel(imageIcon);

            // Add the label and image to the panel
            panel.add(label, BorderLayout.NORTH);
            panel.add(imageLabel, BorderLayout.CENTER);

            // Customize the appearance based on selection
            if (isSelected) {
                panel.setBackground(list.getSelectionBackground());
                panel.setForeground(list.getSelectionForeground());
            } else {
                panel.setBackground(list.getBackground());
                panel.setForeground(list.getForeground());
            }

            return panel;
        }

        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}
*/