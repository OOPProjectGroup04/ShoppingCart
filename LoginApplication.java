import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// UserModel (Model)
class UserModel {
    private List<ChangeListener> listeners = new ArrayList<>();
    private boolean authenticated;
    private String role;

    public void authenticate(String username, String password, String role) {
        boolean oldAuthenticated = this.authenticated;
        this.authenticated = "admin".equalsIgnoreCase(username) && "12345".equalsIgnoreCase(password);
        if (this.authenticated) {
            this.role = role;
        }
        if (oldAuthenticated != this.authenticated) {
            notifyListeners();
        }
    }


    public boolean isAuthenticated() {
        return authenticated;
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }

    public String getRole() {
        return role;
    }

}

// LoginView (View)
class LoginView extends JFrame {
    private JLabel userLabel = new JLabel("Username");
    private JTextField userTextField = new JTextField();
    private JLabel passwordLabel = new JLabel("Password");
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Login");
    private JButton resetButton = new JButton("Reset");
    private JLabel messageLabel = new JLabel();
    private JLabel titleLabel = new JLabel("George's Shopping Cart App", SwingConstants.CENTER);
    private JRadioButton sellerRadioButton = new JRadioButton("Seller");
    private JRadioButton customerRadioButton = new JRadioButton("Customer");
    private ButtonGroup roleButtonGroup = new ButtonGroup();

    public LoginView() {
        setTitle("Login Form");
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(null);

        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setBounds(50, 30, 270, 30);
        container.add(titleLabel);

        sellerRadioButton.setBounds(150, 270, 80, 30);
        customerRadioButton.setBounds(230, 270, 100, 30);

        roleButtonGroup.add(sellerRadioButton);
        roleButtonGroup.add(customerRadioButton);

        container.add(sellerRadioButton);
        container.add(customerRadioButton);
        customerRadioButton.setSelected(true);

        userLabel.setBounds(50, 150, 100, 30);
        container.add(userLabel);

        userTextField.setBounds(150, 150, 150, 30);
        container.add(userTextField);

        passwordLabel.setBounds(50, 220, 100, 30);
        container.add(passwordLabel);

        passwordField.setBounds(150, 220, 150, 30);
        container.add(passwordField);

        loginButton.setBounds(150, 300, 100, 30);
        container.add(loginButton);

        resetButton.setBounds(150, 340, 100, 30);
        container.add(resetButton);

        messageLabel.setBounds(50, 380, 270, 30);
        container.add(messageLabel);
    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public void displayMessage(String message, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(message);
    }

    public void attachController(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
    }

    public void updateLoginStatus(boolean isAuthenticated) {
        String message = isAuthenticated ? "Login successful" : "Invalid username or password";
        displayMessage(message, isAuthenticated ? Color.GREEN : Color.RED);
    }
    public void resetFields() {
    userTextField.setText("");
    passwordField.setText("");
}

    public String getRole() {
        if (sellerRadioButton.isSelected()) {
            return "Seller";
        } else if (customerRadioButton.isSelected()) {
            return "Customer";
        } else {
            return null;
        }
    }
}

// LoginController (Controller)
class LoginController implements ActionListener, ChangeListener {
    private UserModel model;
    private LoginView view;

    public LoginController(UserModel model, LoginView view) {
        this.model = model;
        this.view = view;
        this.view.attachController(this);
        this.model.addChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getLoginButton()) {
            String username = view.getUsername();
            String password = view.getPassword();
            String role = view.getRole(); // Get the selected role from the view
            model.authenticate(username, password, role);
        } else if (e.getSource() == view.getResetButton()) {
            view.resetFields();
        }
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        boolean isAuthenticated = model.isAuthenticated();
        view.updateLoginStatus(isAuthenticated);

        if (isAuthenticated) {
            String role = model.getRole();
            if ("Customer".equals(role)) {
                // Redirect to customer homepage
            } else if ("Seller".equals(role)) {
                // Redirect to seller dashboard
                showSellerDashboard();
            }
        }
    }

    private void showSellerDashboard() {
        SwingUtilities.invokeLater(() -> {
            SellerModel sellerModel = new SellerModel();
            SellerView sellerView = new SellerView();
            SellerController sellerController = new SellerController(sellerModel, sellerView);
            sellerView.display();
        });
    }
}

// Main Application
public class LoginApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserModel model = new UserModel();
            LoginView view = new LoginView();
            new LoginController(model, view);
            view.setVisible(true);
        });
    }
}
