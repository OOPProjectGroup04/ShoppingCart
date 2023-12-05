import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a view for displaying and answering a security question.
 * This class creates the user interface for the security question prompt.
 *
 * @author George Martinez
 */
public class SecurityQuestionView extends JFrame {
    private JLabel securityQuestionLabel = new JLabel("Security Question");
    private JTextField answerTextField = new JTextField();
    private JButton submitButton = new JButton("Submit");

     /**
     * Constructs the SecurityQuestionView with the specified security question.
     *
     * @param securityQuestion The security question to be displayed.
     */
    public SecurityQuestionView(String securityQuestion) {
        setTitle("Security Question");
        setBounds(10, 10, 370, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(null);

        securityQuestionLabel.setBounds(50, 30, 270, 30);
        container.add(securityQuestionLabel);

        JLabel questionLabel = new JLabel(securityQuestion);  // Set the security question text
        questionLabel.setBounds(50, 60, 270, 30);
        container.add(questionLabel);

        answerTextField.setBounds(50, 90, 270, 30);
        container.add(answerTextField);

        submitButton.setBounds(150, 130, 100, 30);
        container.add(submitButton);
    }


     /**
     * Returns the submit button.
     *
     * @return The submit button.
     */
    public JButton getSubmitButton() {
        return submitButton;
    }

    /**
     * Gets the user's answer to the security question.
     *
     * @return The answer text entered by the user.
     */
    public String getAnswer() {
        return answerTextField.getText();
    }

     /**
     * Sets the security question text in the view.
     *
     * @param question The security question to display.
     */
    public void setSecurityQuestion(String question) {
        securityQuestionLabel.setText(question);
    }

    /**
     * Attaches an action listener to the submit button.
     *
     * @param actionListener The ActionListener to be added.
     */
    public void attachController(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }
}
