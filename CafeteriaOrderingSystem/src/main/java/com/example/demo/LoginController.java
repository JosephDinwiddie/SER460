package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginController extends SceneController {

    @FXML
    private VBox loginPrompt; // The VBox containing username/password input fields

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private String selectedRole; // To store the currently selected role

    // Hardcoded credentials for manager and operator
    private final String managerUsername = "manager";
    private final String managerPassword = "manager123";

    private final String operatorUsername = "operator";
    private final String operatorPassword = "operator123";

    @FXML
    public void selectManager(ActionEvent event) {
        selectedRole = "manager";
        showLoginPrompt("Manager");
    }

    @FXML
    public void selectOperator(ActionEvent event) {
        selectedRole = "operator";
        showLoginPrompt("Operator");
    }

    @FXML
    public void loginAsCustomer(ActionEvent event) {
        try {
            switchToCustomerView(event); // Directly go to Customer View
        } catch (Exception e) {
            statusLabel.setText("Error loading customer view.");
            e.printStackTrace();
        }
    }

    @FXML
    public void loginWithCredentials(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validate credentials based on selected role
        if ("manager".equals(selectedRole) && username.equals(managerUsername) && password.equals(managerPassword)) {
            try {
                switchToMainUI(event); // Go to Manager View
            } catch (Exception e) {
                statusLabel.setText("Error loading manager view.");
                e.printStackTrace();
            }
        } else if ("operator".equals(selectedRole) && username.equals(operatorUsername) && password.equals(operatorPassword)) {
            try {
                switchToCafeteriaOperatorView(event); // Go to Operator View
            } catch (Exception e) {
                statusLabel.setText("Error loading operator view.");
                e.printStackTrace();
            }
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    // Show the login prompt for username/password
    private void showLoginPrompt(String role) {
        loginPrompt.setVisible(true);
        statusLabel.setText(""); // Clear any previous status messages
        usernameField.clear();
        passwordField.clear();
    }
}
