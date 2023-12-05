import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the user model in the application. It handles user authentication
 * and security questions related to the user's role.
 *
 * @author Freddy Ingle
 * @author George Martinez
 */
// UserModel (Model)
class UserModel {
    private List<ChangeListener> listeners = new ArrayList<>();
    private boolean authenticated;
    private String role;


    /**
     * Authenticates a user based on the provided credentials and role.
     *
     * @param username The username input.
     * @param password The password input.
     * @param role     The role of the user (e.g., Customer, Seller).
     */
    public void authenticate(String username, String password, String role) {
        boolean oldAuthenticated = this.authenticated;
        this.authenticated = "admin".equalsIgnoreCase(username) && "12345".equalsIgnoreCase(password);

        // Check if authentication is successful
        if (authenticated) {
            this.role = role;

            // Check if security question is needed
            if ("Customer".equals(role)) {
                setSecurityQuestion("What is your favorite color?", "Blue");
            } else if ("Seller".equals(role)) {
                setSecurityQuestion("What is the name of your first pet?", "Fluffy");
            }

            setSecurityQuestionRequired(true);
        }

        // Notify listeners only if authentication status changes
        if (oldAuthenticated != this.authenticated) {
            notifyListeners();
        }
    }


    /**
     * Returns the authentication status of the user.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

      /**
     * Adds a ChangeListener to the list of listeners.
     *
     * @param listener The ChangeListener to be added.
     */
    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all registered listeners of a change in the user's authentication status.
     */
    private void notifyListeners() {
        ChangeEvent event = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(event);
        }
    }

     /**
     * Gets the role of the currently authenticated user.
     *
     * @return A string representing the user's role.
     */
    public String getRole() {
        return role;
    }

    private boolean securityQuestionRequired;
    private String securityQuestion;
    private String securityAnswer;

     /**
     * Sets the security question and answer for the user.
     *
     * @param question The security question.
     * @param answer   The answer to the security question.
     */
    public void setSecurityQuestion(String question, String answer) {
        this.securityQuestion = question;
        this.securityAnswer = answer;
    }

     /**
     * Checks if answering a security question is required for the user.
     *
     * @return true if a security question is required, false otherwise.
     */
    public boolean isSecurityQuestionRequired() {
        return securityQuestionRequired;
    }

     /**
     * Sets whether answering a security question is required for the user.
     *
     * @param required true if a security question is required, false otherwise.
     */
    public void setSecurityQuestionRequired(boolean required) {
        this.securityQuestionRequired = required;
    }

    
    /**
     * Gets the security question for the user.
     *
     * @return A string representing the security question.
     */
    public String getSecurityQuestion() {
        return securityQuestion;
    }

     /**
     * Validates the user's answer to the security question.
     *
     * @param answer The answer to validate.
     * @return true if the answer is correct, false otherwise.
     */
    public boolean validateSecurityAnswer(String answer) {
        return securityAnswer.equalsIgnoreCase(answer);
    }
}

/**
 * This class represents the login view in the application. It creates the user
 * interface for user login, including fields for username, password, and role selection.
 *
 */
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

    /**
     * Constructs the login view with all UI components.
     */
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

     /**
     * Gets the entered username.
     *
     * @return The text entered in the username field.
     */
    public String getUsername() {
        return userTextField.getText();
    }

     /**
     * Gets the entered password.
     *
     * @return The text entered in the password field.
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Returns the login button.
     *
     * @return The login button.
     */
    public JButton getLoginButton() {
        return loginButton;
    }

     /**
     * Returns the reset button.
     *
     * @return The reset button.
     */
    public JButton getResetButton() {
        return resetButton;
    }

    /**
     * Displays an error message dialog.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    /**
     * Displays a message in the view with the specified color.
     *
     * @param message The message to display.
     * @param color   The color of the message text.
     */
    public void displayMessage(String message, Color color) {
        messageLabel.setForeground(color);
        messageLabel.setText(message);
    }

     /**
     * Attaches the given action listener to the login and reset buttons.
     *
     * @param actionListener The ActionListener to attach.
     */
    public void attachController(ActionListener actionListener) {
        loginButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
    }

     /**
     * Updates the login status message in the view.
     *
     * @param isAuthenticated The authentication status of the user.
     */
    public void updateLoginStatus(boolean isAuthenticated) {
        String message = isAuthenticated ? "Login successful" : "Invalid username or password";
        displayMessage(message, isAuthenticated ? Color.GREEN : Color.RED);
    }
    /**
     * Resets the fields in the login form.
     */
    public void resetFields() {
    userTextField.setText("");
    passwordField.setText("");
}

    /**
     * Gets the selected role from the radio buttons.
     *
     * @return The selected role, either "Seller" or "Customer".
     */
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

/**
 * This class serves as the controller in the MVC pattern. It handles user interactions
 * from the LoginView and updates the UserModel and other views accordingly.
 */
// LoginController (Controller)
class LoginController implements ActionListener, ChangeListener {
    private UserModel model;
    private LoginView view;
    private CatalogView catalogView;
    private SecurityQuestionView securityQuestionView;
    private boolean securityQuestionAnswered = false;

    
    /**
     * Constructs a LoginController with the specified model, view, and catalog view.
     *
     * @param model       The UserModel to manage authentication logic.
     * @param view        The LoginView to interact with the user.
     * @param catalogView The CatalogView for displaying the product catalog.
     */
    public LoginController(UserModel model, LoginView view, CatalogView catalogView) {
        this.model = model;
        this.view = view;
        this.catalogView = catalogView;
        this.securityQuestionView = new SecurityQuestionView(model.getSecurityQuestion());
        this.view.attachController(this);
        this.model.addChangeListener(this);
        this.securityQuestionView.attachController(this); // Attach the controller to handle the submit button
    }

     /**
     * Handles action events triggered in the LoginView.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getLoginButton()) {
            String username = view.getUsername();
            String password = view.getPassword();
            String role = view.getRole();
            model.authenticate(username, password, role);

            if (model.isAuthenticated()) {
                // Check if security question is needed
                if (model.isSecurityQuestionRequired()) {
                    showSecurityQuestion();
                } else {
                    // Proceed with role-based redirection
                    handleAuthenticationSuccess();
                }
            }
        } else if (e.getSource() == view.getResetButton()) {
            view.resetFields();
        } else if (e.getSource() == securityQuestionView.getSubmitButton()) {
            handleSecurityQuestionSubmission();
        }
    }

    /**
     * Displays the security question view.
     */
    private void showSecurityQuestion() {
        // Set the security question text before showing the view
        securityQuestionView.setSecurityQuestion(model.getSecurityQuestion());
        securityQuestionView.setVisible(true);
    }

     /**
     * Handles the submission of the security question.
     */
    private void handleSecurityQuestionSubmission() {
        String answer = securityQuestionView.getAnswer();
        if (model.validateSecurityAnswer(answer)) {
            securityQuestionAnswered = true;
            securityQuestionView.dispose();  // Close the security question dialog
            redirectToRoleHomepage(model.getRole());
        } else {
            securityQuestionView.dispose();  // Close the security question dialog
            view.displayErrorMessage("Incorrect answer to the security question. Authentication failed.");
        }
    }


    /**
     * Manages the flow after successful authentication, including security question handling.
     */
    private void handleAuthenticationSuccess() {
        String role = model.getRole();
        if ("Customer".equals(role) || "Seller".equals(role)) {
            // Display security question and wait for submission
            showSecurityQuestion();
        } else {
            // Handle other roles or scenarios
            redirectToRoleHomepage(role);
        }
    }

     /**
     * Redirects the user to the appropriate homepage based on their role.
     *
     * @param role The role of the user.
     */
    private void redirectToRoleHomepage(String role) {
        securityQuestionView.dispose();  // Close the security question dialog

        if ("Customer".equals(role)) {
            // Redirect to customer homepage
            catalogView.displayCustomerHomepage();
        } else if ("Seller".equals(role)) {
            // Redirect to seller dashboard
            showSellerDashboard();
        }
    }

    /**
     * Responds to state changes in the UserModel.
     *
     * @param e The change event.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        boolean isAuthenticated = model.isAuthenticated();
        view.updateLoginStatus(isAuthenticated);

        if (isAuthenticated && securityQuestionAnswered) {
            String role = model.getRole();
            redirectToRoleHomepage(role);
            securityQuestionAnswered = false;  // Reset the flag for future logins
        }
    }

     /**
     * Shows the seller dashboard.
     */
    private void showSellerDashboard() {
        SwingUtilities.invokeLater(() -> {
            SellerModel sellerModel = new SellerModel();
            SellerView sellerView = new SellerView();
            SellerController sellerController = new SellerController(sellerModel, sellerView);
            sellerView.display();
        });
    }
}
/**
 * This is the main class for the login application. It sets up the application
 * by initializing the model, view, and controller, and then makes the view visible.
 */
// Main Application
public class LoginApplication {
    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserModel model = new UserModel();
            LoginView view = new LoginView();
            CatalogView catalogView = new CatalogView();  // Create an instance of CatalogView
            new LoginController(model, view, catalogView);  // Pass catalogView to the constructor
            view.setVisible(true);
        });
    }
}
