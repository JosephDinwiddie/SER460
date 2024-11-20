package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMenuItemController {

    @FXML
    private TextField itemIdField;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField mealTypeField;

    @FXML
    private TextField priceField;

    private Menu menu; // Reference to the Menu object

    // Setter for the menu object
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @FXML
    public void saveMenuItem(ActionEvent event) {
        try {
            // Parse input fields
            int id = Integer.parseInt(itemIdField.getText());
            String name = itemNameField.getText();
            String mealType = mealTypeField.getText();
            double price = Double.parseDouble(priceField.getText());

            // Create a new MenuItem and add it to the menu
            MenuItem newItem = MenuItemFactory.createMenuItem(id, name, mealType, price);
            menu.addMenuItem(newItem);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Menu item added successfully!");
            alert.showAndWait();

            // Close the current stage
            Stage stage = (Stage) itemIdField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            // Show error alert if parsing fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText("Please ensure all fields are filled out correctly.");
            alert.showAndWait();
        }
    }

    @FXML
    public void cancel(ActionEvent event) {
        // Close the current stage
        Stage stage = (Stage) itemIdField.getScene().getWindow();
        stage.close();
    }
}
