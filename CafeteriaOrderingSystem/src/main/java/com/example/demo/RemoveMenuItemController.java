package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RemoveMenuItemController {

    @FXML
    private VBox menuVBox; // Container for checkboxes

    private Menu menu; // Reference to the shared menu instance
    private List<MenuItem> selectedItems; // List of selected items for removal

    // Setter to inject the menu instance
    public void setMenu(Menu menu) {
        this.menu = menu;
        loadMenuItems(); // Populate the VBox with menu items
    }

    // Load menu items into the VBox with checkboxes
    private void loadMenuItems() {
        menuVBox.getChildren().clear(); // Clear any existing items
        selectedItems = new ArrayList<>(); // Initialize the selected items list

        for (MenuItem item : menu.getMenu()) {
            CheckBox checkBox = new CheckBox(item.getName() + " - $" + item.getPrice());
            checkBox.setUserData(item); // Store the MenuItem object in the checkbox
            menuVBox.getChildren().add(checkBox);
        }
    }

    @FXML
    public void confirmRemoval(ActionEvent event) {
        // Collect selected items
        selectedItems.clear();
        for (javafx.scene.Node node : menuVBox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    selectedItems.add((MenuItem) checkBox.getUserData());
                }
            }
        }

        if (selectedItems.isEmpty()) {
            showAlert("No Items Selected", "Please select at least one item to remove.");
            return;
        }

        // Ask for confirmation
        String itemList = selectedItems.stream()
                .map(MenuItem::getName)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Removal");
        confirmationAlert.setHeaderText("Are you sure you want to remove these items?");
        confirmationAlert.setContentText(itemList);

        // Handle confirmation result
        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                removeSelectedItems();
                closeWindow();
            }
        });
    }

    // Remove selected items from the menu
    private void removeSelectedItems() {
        for (MenuItem item : selectedItems) {
            menu.removeMenuItem(item.getId());
        }
        showAlert("Items Removed", "The selected items have been removed.");
    }

    @FXML
    public void cancel(ActionEvent event) {
        closeWindow();
    }

    // Utility to close the current window
    private void closeWindow() {
        Stage stage = (Stage) menuVBox.getScene().getWindow();
        stage.close();
    }

    // Utility to show an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
