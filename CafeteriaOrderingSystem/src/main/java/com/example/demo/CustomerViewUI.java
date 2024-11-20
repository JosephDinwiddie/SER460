package com.example.demo;

import java.io.IOException;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;


public class CustomerViewUI extends SceneController {

    @FXML
    private TextField orderIdField;

    @FXML
    private Label statusLabel;

    @FXML
    private TabPane tabPane;

    @FXML
    private ScrollPane placeOrderScrollPane;

    @FXML
    private VBox placeOrderLayout;

    private Menu menu;
    private OrderManager orderManager;
    private List<MenuItem> selectedItems;

    public CustomerViewUI() {
        menu = Menu.getInstance(); // Use the Singleton instance of Menu
        orderManager = orderManager.getInstance();
        selectedItems = new ArrayList<>();
    }
    

    @Override
    public void switchToMainUI(ActionEvent event) {
        try {
            super.switchToMainUI(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        tabPane.getTabs().forEach(tab -> tab.setClosable(false)); // Disable closing tabs
        placeOrderScrollPane.setContent(placeOrderLayout); // Set VBox as ScrollPane content
        setupMenuSelectionTab(); // For "Display Menu" tab
        setupOrderTab(); // For "Place Order" tab
    }

      private void setupMenuSelectionTab() {
        VBox menuSelectionLayout = new VBox(10);
        menuSelectionLayout.setPadding(new Insets(10));

        // Cafeteria Manager: Shared menu buttons for consistency
        Label menuLabel = new Label("Select a Menu to View Items:");
        Button breakfastButton = new Button("Breakfast");
        Button lunchButton = new Button("Lunch");
        Button dinnerButton = new Button("Dinner");

        // Set button actions to display the static menu
        breakfastButton.setOnAction(e -> displayStaticMenu("Breakfast"));
        lunchButton.setOnAction(e -> displayStaticMenu("Lunch"));
        dinnerButton.setOnAction(e -> displayStaticMenu("Dinner"));

        menuSelectionLayout.getChildren().addAll(menuLabel, breakfastButton, lunchButton, dinnerButton);

        // Populate the "Display Menu" tab with menu selection layout
        tabPane.getTabs().get(0).setContent(menuSelectionLayout);
    }

    private void displayStaticMenu(String mealType) {
        try {
            // Create a new stage for the pop-up
            Stage popupStage = new Stage();
            popupStage.setTitle(mealType + " Menu");
    
            // Create a VBox to hold the menu and the back button
            VBox popupLayout = new VBox(10);
            popupLayout.setPadding(new Insets(10));
            popupLayout.setAlignment(Pos.CENTER);
    
            // Add a label for the menu type
            Label menuLabel = new Label(mealType + " Menu:");
    
            // Create the scrollable menu layout
            ScrollPane menuPane = MenuDisplayUtil.createScrollableMenu(menu, mealType);
    
            // Add a Close button to return to the main display
            Button closeButton = new Button("Close");
            closeButton.setOnAction(e -> popupStage.close());
    
            // Add elements to the VBox
            popupLayout.getChildren().addAll(menuLabel, menuPane, closeButton);
    
            // Set the scene for the pop-up window
            Scene popupScene = new Scene(popupLayout, 500, 800); // Adjust size as needed
            popupStage.setScene(popupScene);
    
            // Set modality to block interaction with the main window while the pop-up is open
            popupStage.initModality(Modality.APPLICATION_MODAL);
    
            // Show the pop-up window
            popupStage.showAndWait();
        } catch (Exception e) {
            showAlert("Error", "Failed to load menu for " + mealType);
        }
    }
    
    

    private void displayMenu(String mealType) {
        placeOrderLayout.getChildren().clear();
    
        Label menuLabel = new Label(mealType + " Menu:");
        List<MenuItem> items = menu.filterByMealType(mealType);
        VBox itemLayout = new VBox(5);
    
        for (MenuItem item : items) {
            Button itemButton = new Button(item.getName() + " - $" + item.getPrice());
            itemButton.setOnAction(e -> selectedItems.add(item)); // Add items to the same list
            itemLayout.getChildren().add(itemButton);
        }
    
        Button finishOrderButton = new Button("Finish Order");
        finishOrderButton.setOnAction(e -> promptForCustomerID()); // Prompt for Customer ID
    
        Button backButton = new Button("Back to Menu Selection");
        backButton.setOnAction(e -> goBackToMenuSelection());
    
        placeOrderLayout.getChildren().addAll(menuLabel, itemLayout, finishOrderButton, backButton);
    }

    private void setupOrderTab() {
        placeOrderLayout.getChildren().clear();

        // Initial button to start the order process
        Button startOrderButton = new Button("Place an Order");
        startOrderButton.setOnAction(e -> startOrderProcess());

        placeOrderLayout.getChildren().add(startOrderButton);
    }

    @FXML
    private void startOrderProcess() {
        placeOrderLayout.getChildren().clear();

        Label selectMenuLabel = new Label("Select a menu to view items:");
        Button breakfastButton = new Button("Breakfast");
        Button lunchButton = new Button("Lunch");
        Button dinnerButton = new Button("Dinner");

        breakfastButton.setOnAction(e -> displayMenu("Breakfast"));
        lunchButton.setOnAction(e -> displayMenu("Lunch"));
        dinnerButton.setOnAction(e -> displayMenu("Dinner"));

        placeOrderLayout.getChildren().addAll(selectMenuLabel, breakfastButton, lunchButton, dinnerButton);
    }


    private void promptForCustomerID() {
        placeOrderLayout.getChildren().clear();

        Label promptLabel = new Label("Enter Name:");
        TextField customerIDField = new TextField();
        customerIDField.setPromptText("Name");

        Button confirmButton = new Button("Confirm");
        confirmButton.setOnAction(e -> {
            String customerID = customerIDField.getText();
            if (customerID == null || customerID.trim().isEmpty()) {
                showAlert("Invalid Input", "Name cannot be empty.");
            } else {
                showFinalizeOrderPage(customerID);
            }
        });

        placeOrderLayout.getChildren().addAll(promptLabel, customerIDField, confirmButton);
    }

    private void showFinalizeOrderPage(String customerID) {
        placeOrderLayout.getChildren().clear();
    
        Label finalizeLabel = new Label("Finalize Your Order:");
        Label customerIDLabel = new Label("Customer ID: " + customerID);
        VBox itemLayout = new VBox(5);
    
        // Use an array to hold the mutable totalCost
        final double[] totalCost = {0};
    
        for (MenuItem item : selectedItems) {
            totalCost[0] += item.getPrice();
            Button removeButton = new Button("Remove " + item.getName());
            removeButton.setOnAction(e -> {
                selectedItems.remove(item);
                totalCost[0] -= item.getPrice(); // Update totalCost
                showFinalizeOrderPage(customerID); // Refresh the page after item removal
            });
            itemLayout.getChildren().add(new Label(item.getName() + " - $" + item.getPrice()));
            itemLayout.getChildren().add(removeButton);
        }
    
        Label totalLabel = new Label("Total Cost: $" + String.format("%.2f", totalCost[0]));
        Button submitButton = new Button("Submit Order");
        submitButton.setOnAction(e -> promptForPayment(customerID, totalCost[0])); // Prompt for payment
    
        // Add a spacer region to push the button into view
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
    
        placeOrderLayout.getChildren().addAll(finalizeLabel, customerIDLabel, itemLayout, totalLabel, spacer, submitButton);
    }
    
    
    
    private void promptForPayment(String customerID, double totalCost) {
        Stage paymentStage = new Stage();
        paymentStage.setTitle("Payment");
    
        VBox paymentLayout = new VBox(10);
        paymentLayout.setPadding(new Insets(10));
        paymentLayout.setAlignment(Pos.CENTER);
    
        Label paymentLabel = new Label("Enter Credit Card Number:");
        TextField creditCardField = new TextField();
        creditCardField.setPromptText("16-digit credit card number");
    
        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            String creditCardNumber = creditCardField.getText();
            if (isValidCreditCard(creditCardNumber)) {
                paymentStage.close();
                submitOrder(customerID, totalCost); // Proceed with order submission
            } else {
                showAlert("Invalid Payment", "The credit card number is invalid. Please try again.");
            }
        });
    
        paymentLayout.getChildren().addAll(paymentLabel, creditCardField, payButton);
    
        Scene scene = new Scene(paymentLayout, 300, 150);
        paymentStage.setScene(scene);
        paymentStage.initModality(Modality.APPLICATION_MODAL);
        paymentStage.showAndWait();
    }

    private boolean isValidCreditCard(String creditCardNumber) {
        if (creditCardNumber == null) {
            return false;
        }
    
        // Remove all spaces and hyphens from the input
        String sanitized = creditCardNumber.replaceAll("[\\s-]", "");
    
        // Ensure the sanitized input is a 16-digit number
        if (!sanitized.matches("\\d{16}")) {
            return false;
        }
    
        // Apply Luhn Algorithm to validate the card number
        int sum = 0;
        boolean alternate = false;
        for (int i = sanitized.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(sanitized.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
    
    
    

    private void submitOrder(String customerID, double totalCost) {
        try {
            // Place the order and generate an Order ID
            int orderID = orderManager.placeOrder(customerID, selectedItems);
    
            // Show success message
            placeOrderLayout.getChildren().clear();
            Label successLabel = new Label("Order placed successfully!");
            Label orderIDLabel = new Label("Order ID: " + orderID);
            Label totalCostLabel = new Label("Total Paid: $" + String.format("%.2f", totalCost));
    
            placeOrderLayout.getChildren().addAll(successLabel, orderIDLabel);
    
            // Reset the Place Order tab after a short delay to allow user to see success message
            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(e -> setupOrderTab());
            delay.play();
        } catch (IllegalArgumentException e) {
            showAlert("Order Error", e.getMessage());
        }
    }
    

    

    private void showAlert(String title, String message) {
        Stage alertStage = new Stage();
        alertStage.setTitle(title);

        VBox alertLayout = new VBox(10);
        alertLayout.setPadding(new Insets(10));
        alertLayout.setAlignment(Pos.CENTER);

        Label messageLabel = new Label(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> alertStage.close());

        alertLayout.getChildren().addAll(messageLabel, closeButton);

        Scene scene = new Scene(alertLayout, 300, 150);
        alertStage.setScene(scene);
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.showAndWait();
    }

    @FXML
    private void checkOrderStatus(ActionEvent event) {
        String orderIdText = orderIdField.getText();
    
        try {
            int orderID = Integer.parseInt(orderIdText); // Convert String to int
            String status = orderManager.getOrderStatus(orderID); // Call with int
            statusLabel.setText(status);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid Order ID. Please enter a numeric value.");
        }
    }

    @FXML
    private void goBackToMenuSelection() {
        placeOrderLayout.getChildren().clear();

        Label selectMenuLabel = new Label("Select a menu to view items:");
        Button breakfastButton = new Button("Breakfast");
        Button lunchButton = new Button("Lunch");
        Button dinnerButton = new Button("Dinner");

        // Add functionality to menu buttons
        breakfastButton.setOnAction(e -> displayMenu("Breakfast"));
        lunchButton.setOnAction(e -> displayMenu("Lunch"));
        dinnerButton.setOnAction(e -> displayMenu("Dinner"));

        placeOrderLayout.getChildren().addAll(selectMenuLabel, breakfastButton, lunchButton, dinnerButton);
    }

}
