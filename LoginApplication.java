import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// LoginModel
class LoginModel {
    public boolean authenticate(String username, String password) {
    
        return "admin".equalsIgnoreCase(username) && "12345".equalsIgnoreCase(password);
    }
}

// LoginView
class LoginView extends JFrame {
    JLabel userLabel = new JLabel("Username");
    JTextField userTextField = new JTextField();
    JLabel passwordLabel = new JLabel("Password");
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JLabel messageLabel = new JLabel();

    public LoginView() {
        // Setup GUI components
        // Set the frame properties
        setTitle("Login Form");
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the container and layout manager
        Container container = getContentPane();
        container.setLayout(null);

        // Create and place the username label
        userLabel = new JLabel("Username");
        userLabel.setBounds(50, 150, 100, 30);
        container.add(userLabel);

        // Create and place the username text field
        userTextField = new JTextField();
        userTextField.setBounds(150, 150, 150, 30);
        container.add(userTextField);

        // Create and place the password label
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 220, 100, 30);
        container.add(passwordLabel);

        // Create and place the password field
        passwordField = new JPasswordField();
        passwordField.setBounds(150, 220, 150, 30);
        container.add(passwordField);

        // Create and place the login button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 300, 100, 30);
        container.add(loginButton);
    }

    // Getters for the controller to access the view's components
    public String getUsername() {
        return userTextField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    

    public void displayMessage(String message, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(message);
    }
}

// LoginController
class LoginController implements ActionListener {
    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
        this.view.loginButton.addActionListener(this);
        this.view.resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle login action
        if (e.getSource() == view.loginButton) {
            String username = view.userTextField.getText();
            String password = new String(view.passwordField.getPassword());

            if (model.authenticate(username, password)) {
                view.displayMessage("Login successful", Color.GREEN);
            } else {
                view.displayMessage("Invalid username or password", Color.RED);
            }
        }

        // Handle reset action
        if (e.getSource() == view.resetButton) {
            view.userTextField.setText("");
            view.passwordField.setText("");
        }
    }
}

// Main Application
public class LoginApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginModel model = new LoginModel();
            LoginView view = new LoginView();
            new LoginController(model, view);
            view.setVisible(true);
        });
    }
}
