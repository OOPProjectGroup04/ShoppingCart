import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class CatalogView {
    private JFrame frame;
    private JPanel productPanel;

    public CatalogView() {
        frame = new JFrame("Product Catalog");
        productPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns, auto rows, 10px gaps
        JScrollPane scrollPane = new JScrollPane(productPanel);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JLabel nameLabel = new JLabel(product.getName());
        JLabel priceLabel = new JLabel(String.format("$%.2f", product.getPrice()));
        JLabel descriptionLabel = new JLabel("<html>" + product.getDescription() + "</html>");
        descriptionLabel.setVerticalAlignment(SwingConstants.TOP);

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
                    card.add(imageLabel, BorderLayout.CENTER);
                    card.revalidate();
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the error here
                }
            }
        }.execute();

        card.add(nameLabel, BorderLayout.NORTH);
        card.add(priceLabel, BorderLayout.SOUTH);
        card.add(descriptionLabel, BorderLayout.EAST);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        return card;
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


