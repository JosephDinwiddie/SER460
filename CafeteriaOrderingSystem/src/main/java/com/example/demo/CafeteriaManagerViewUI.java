package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
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
            showUnderConstructionPage(event);
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
            showUnderConstructionPage(event);
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
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    public void viewMenu(ActionEvent event) {
        // Add code to display menu to the UI
        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void addMenuItem(ActionEvent event) {
        // Define the meal options
        List<String> mealOptions = Arrays.asList("Breakfast", "Lunch", "Dinner");

        // Create a ChoiceDialog for selecting a meal type
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Breakfast", mealOptions);
        dialog.setTitle("Select Meal Type");
        dialog.setHeaderText("Add Menu Item");
        dialog.setContentText("Choose the meal type for the new item:");

        // Show the dialog and capture the result
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // If a choice was made, show the "Under Construction" page
            try {
                showUnderConstructionPage(event);
            } catch (IOException e) {
                // Handle the exception by showing an alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to Load Page");
                alert.setContentText("An error occurred while loading the Under Construction page.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void removeMenuItem(ActionEvent event) {
        // Define the meal options
        List<String> mealOptions = Arrays.asList("Breakfast", "Lunch", "Dinner");

        // Create a ChoiceDialog for selecting a meal type
        ChoiceDialog<String> dialog = new ChoiceDialog<>("Breakfast", mealOptions);
        dialog.setTitle("Select Meal Type");
        dialog.setHeaderText("Add Menu Item");
        dialog.setContentText("Choose the meal type for the new item:");

        // Show the dialog and capture the result
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            // If a choice was made, show the "Under Construction" page
            try {
                showUnderConstructionPage(event);
            } catch (IOException e) {
                // Handle the exception by showing an alert
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to Load Page");
                alert.setContentText("An error occurred while loading the Under Construction page.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
    }

}
