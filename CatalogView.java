import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;





public class CatalogView {
    private JFrame frame;
    private JList<Product> productList;
    private DefaultListModel<Product> productListModel;

    public CatalogView() {
        frame = new JFrame("Product Catalog");
        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        frame.add(new JScrollPane(productList), BorderLayout.CENTER);
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        productList.setCellRenderer(new ProductCellRenderer());

        
        
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    public void setProductListModel(DefaultListModel<Product> model) {
        this.productListModel = model;
        productList.setModel(model);
    }

    public JList<Product> getProductList() {
        return productList;
    }

    // Additional methods to update UI components
}


 class ProductCatalogController {
    private ProductModel model;
    private CatalogView view;

    public ProductCatalogController(ProductModel model, CatalogView view) {
        this.model = model;
        this.view = view;
        this.model.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateView();
            }
        });
        updateView();
    }

    private void updateView() {
        DefaultListModel<Product> listModel = new DefaultListModel<>();
        for (Product product : model.getProducts()) {
            listModel.addElement(product);
        }
        view.setProductListModel(listModel);
    }

   

    // Additional methods for handling user actions
}

 class CatalogMain {
    public static void main(String[] args) {
        ProductModel model = new ProductModel();
        CatalogView view = new CatalogView();
        ProductCatalogController controller = new ProductCatalogController(model, view);
        view.setVisible(true);
    }
}

