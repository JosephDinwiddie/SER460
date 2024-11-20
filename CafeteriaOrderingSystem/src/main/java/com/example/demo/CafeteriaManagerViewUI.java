package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CafeteriaManagerViewUI extends SceneController{
     @FXML
    private Button viewOrdersButton;

    @FXML
    private Button processOrderButton;

    @FXML
    private Button updateOrderStatusButton;

    @FXML
    private Button viewMenuButton;

    @FXML
    private Button addMenuItemButton;

    // Cafeteria Manager: Define the menu object
    private Menu menu;

    public CafeteriaManagerViewUI() {
        menu = Menu.getInstance(); // Initialize the menu with predetermined items
    }



    @Override
    public void switchToMainUI(ActionEvent event) throws IOException {
        super.switchToMainUI(event);
    }

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

    @FXML
    public void viewOrders(ActionEvent event) {
         //cafeteriaOperator.viewOrders();
        // Add code to display orders to the UI
        try {
            switchToCafeteriaManagerViewUIViewOrders(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void processOrder(ActionEvent event) {
        //Order order = new Order();
        //cafeteriaOperator.processOrder(order);
        // Add code to update UI based on processing result
        try {
            switchToCafeteriaOperatorViewUIProcessOrders(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void updateOrderStatus(ActionEvent event) {
        //cafeteriaOperator.updateOrderStatus();
        // Add code to reflect status update in UI
        try {
            switchToCafeteriaManagerViewUIProcessOrders(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void viewMenu(ActionEvent event) {
        try {
            // Use the grouped menu as the default
            ScrollPane menuPane = MenuDisplayUtil.createGroupedMenu(menu);
    
            // Show the grouped menu in a new stage
            Stage stage = new Stage();
            stage.setTitle("Cafeteria Menu - Grouped by Meal Type");
            stage.setScene(new Scene(menuPane, 400, 600)); // Set desired dimensions
            stage.show();
        } catch (Exception e) {
            try {
                showUnderConstructionPage(event);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @FXML
private void addMenuItem(ActionEvent event) {
    try {
        // Call the SceneController method to switch to the Add Menu Item scene
        switchToAddMenuItemView(event, menu);
    } catch (IOException e) {
        // Handle any errors
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Unable to Load Add Menu Item Page");
        alert.setContentText("An error occurred while opening the Add Menu Item page.");
        alert.showAndWait();
        e.printStackTrace();
    }
}


@FXML
private void removeMenuItem(ActionEvent event) {
    try {
        switchToRemoveMenuItemView(event, menu); // Open the Remove Menu Item scene
    } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Unable to Load Remove Menu Item Page");
        alert.setContentText("An error occurred while opening the Remove Menu Item page.");
        alert.showAndWait();
        e.printStackTrace();
    }
}


private void showErrorDialog(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Operation Failed");
    alert.setContentText(message);
    alert.showAndWait();
}



 
}
