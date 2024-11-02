package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class OrderStatusController {

    @FXML
    private TextField orderIdField;

    @FXML
    private Label statusLabel;

    private Scene previousScene;

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    @FXML
    private void goBack(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        }
    }

    @FXML
    private void checkOrderStatus(ActionEvent event) {
        String orderId = orderIdField.getText();
        // Logic to check the order status based on orderId
        String status = "Order not found"; // Replace with actual status checking logic
        statusLabel.setText("Status: " + status);
    }
}