package com.example.demo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerViewUI extends SceneController {

    @FXML
    private TextField orderIdField;

    @FXML
    private Label statusLabel;

    @Override
    public void switchToMainUI(ActionEvent event) {
        // Switch back to the main UI
        try {
            super.switchToMainUI(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //remove when implementing the actual functionality
    private void showUnderConstructionPage(ActionEvent event) throws IOException {
        // Load the UnderConstructionPage FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UnderConstructionPage.fxml"));
        Parent root = loader.load();

        // Get the UnderConstructionController and set the previous scene
        UnderConstructionController controller = loader.getController();
        Scene currentScene = ((javafx.scene.Node) event.getSource()).getScene();
        controller.setPreviousScene(currentScene);  // Pass the current scene as the previous scene

        // Show the Under Construction page
        Stage stage = (Stage) currentScene.getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Menu Selection methods
    @FXML
    private void openBreakfastMenu(ActionEvent event) {
        // Logic for displaying breakfast menu
        System.out.println("Displaying breakfast menu...");

        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void openLunchMenu(ActionEvent event) {
        // Logic for displaying lunch menu
        System.out.println("Displaying lunch menu...");

        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void openDinnerMenu(ActionEvent event) {
        // Logic for displaying dinner menu
        System.out.println("Displaying dinner menu...");

        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Order Status method
    @FXML
    private void checkOrderStatus(ActionEvent event) {
        String orderId = orderIdField.getText();
        // Logic to check the order status based on the entered order ID
        String status = "Order not found";  // Replace with actual order status logic
        statusLabel.setText("Status: " + status);
    }
}