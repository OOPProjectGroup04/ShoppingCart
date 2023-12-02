import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecurityQuestionView extends JFrame {
    private JLabel securityQuestionLabel = new JLabel("Security Question");
    private JTextField answerTextField = new JTextField();
    private JButton submitButton = new JButton("Submit");

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


    public JButton getSubmitButton() {
        return submitButton;
    }

    public String getAnswer() {
        return answerTextField.getText();
    }

    public void setSecurityQuestion(String question) {
        securityQuestionLabel.setText(question);
    }

    public void attachController(ActionListener actionListener) {
        submitButton.addActionListener(actionListener);
    }
}
