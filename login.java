package elektronik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class login {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/barang";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JFrame frame;

    public login() {
        frame = new JFrame("Login form");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 250));
        frame.setResizable(false);

        frame.add(panel1());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel panel1() {
        JPanel panel = new JPanel(new GridLayout(4, 1));

        panel.add(new JLabel("Username:"));
        textField1 = new JTextField();
        panel.add(textField1);

        panel.add(new JLabel("Password:"));
        passwordField1 = new JPasswordField();
        panel.add(passwordField1);

        button1 = new JButton("Login");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateLogin()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    openMainForm();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password");
                }
            }
        });
        panel.add(button1);

        return panel;
    }

    private boolean validateLogin() {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String query = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void openMainForm() {
        Mainscreen mainscreen = new Mainscreen();
        mainscreen.setVisible(true);

        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login();
            }
        });
    }
}
