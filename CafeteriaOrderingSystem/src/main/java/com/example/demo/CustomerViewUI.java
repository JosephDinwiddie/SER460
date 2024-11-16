package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomerViewUI extends SceneController {

    @FXML
    private TextField orderIdField;

    @FXML
    private Label statusLabel;

    private Customer customer;
    private Menu menu;

    public CustomerViewUI() {
        menu = new Menu();
        customer = new Customer(1, "John Doe", "123-456-7890");
    }

    @Override
    public void switchToMainUI(ActionEvent event) {
        // Switch back to the main UI
        try {
            super.switchToMainUI(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openBreakfastMenu(ActionEvent event) {
        displayMenuPopup("Breakfast");
    }

    @FXML
    private void openLunchMenu(ActionEvent event) {
        displayMenuPopup("Lunch");
    }

    @FXML
    private void openDinnerMenu(ActionEvent event) {
        displayMenuPopup("Dinner");
    }

    private void displayMenuPopup(String mealType) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle(mealType + " Menu");

        VBox layout = new VBox(10);
        List<MenuItem> items = menu.filterByMealType(mealType);

        for (MenuItem item : items) {
            Text itemDisplay = new Text(item.getName() + " - $" + item.getPrice());
            layout.getChildren().add(itemDisplay);
        }

        Scene scene = new Scene(layout, 300, 400);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }

    @FXML
    private void placeOrder(ActionEvent event) {
        Stage orderStage = new Stage();
        orderStage.initModality(Modality.APPLICATION_MODAL);
        orderStage.setTitle("Place Order");

        VBox layout = new VBox(10);

        TextField orderInput = new TextField();
        orderInput.setPromptText("Enter selected items by ID, separated by commas");

        Label orderStatus = new Label();

        orderInput.setOnAction(e -> {
            String input = orderInput.getText();
            if (input != null && !input.isEmpty()) {
                String[] itemIds = input.split(",");
                List<MenuItem> selectedItems = new ArrayList<>();

                for (String id : itemIds) {
                    try {
                        int itemId = Integer.parseInt(id.trim());
                        MenuItem item = menu.getMenu().stream()
                                .filter(menuItem -> menuItem.getId() == itemId)
                                .findFirst()
                                .orElse(null);
                        if (item != null) {
                            selectedItems.add(item);
                        }
                    } catch (NumberFormatException ex) {
                        // Invalid input, skip
                    }
                }

                if (!selectedItems.isEmpty()) {
                    customer.placeOrder(selectedItems);
                    orderStatus.setText("Order placed successfully!");
                } else {
                    orderStatus.setText("Invalid or no items selected.");
                }
            }
        });

        layout.getChildren().addAll(orderInput, orderStatus);
        Scene scene = new Scene(layout, 400, 300);
        orderStage.setScene(scene);
        orderStage.showAndWait();
    }

    @FXML
    private void checkOrderStatus(ActionEvent event) {
        String orderId = orderIdField.getText();
        if (orderId == null || orderId.isEmpty()) {
            statusLabel.setText("Please enter a valid order ID.");
            return;
        }
        String status = customer.checkOrderStatus(orderId);
        statusLabel.setText("Status: " + status);
    }
}
