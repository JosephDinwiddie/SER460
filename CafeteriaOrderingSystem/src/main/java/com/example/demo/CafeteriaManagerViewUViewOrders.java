package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class CafeteriaManagerViewUViewOrders extends SceneController {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button viewOrderDetailsButton;

    @FXML
    private Button backButton;

    @FXML
    private Label labelCustomerDetails;

    @FXML
    private Label labelItemsOrdered;

    @FXML
    private Label labelOrderID;

    @FXML
    private Label labelOrderStatus;

    @FXML
    private Label labelQuantity;

    @FXML
    private Label labelTotalAmount;

    private CafeteriaOperator cafeteriaOperator = CafeteriaOperator.getInstance();

    @FXML
    private void initialize() {
        // Load order IDs into the ListView
        listView.getItems().addAll(cafeteriaOperator.getDashboard().getAllOrdersIDs());

        // Disable the pop-out button until an order is selected
        viewOrderDetailsButton.setDisable(true);

        // Add a listener to enable the pop-out button when an order is selected
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            viewOrderDetailsButton.setDisable(newValue == null);
            if (newValue != null) {
                updateUI(newValue);
            }
        });
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
        super.switchToCafeteriaManagerView(event);
    }

    @FXML
    private void popOutOrderDetails(ActionEvent event) {
        String selectedOrderID = listView.getSelectionModel().getSelectedItem();

        if (selectedOrderID == null) {
            showAlert("No Selection", "Please select an order to view its details.");
            return;
        }

        try {
            Order selectedOrder = cafeteriaOperator.viewOrders().stream()
                    .filter(order -> String.valueOf(order.getOrderID()).equals(selectedOrderID))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Order not found"));

            showOrderItemsWindow(selectedOrder.getItems());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load order details.");
        }
    }

    private void updateUI(String selectedOrderID) {
        try {
            Order selectedOrder = cafeteriaOperator.viewOrders().stream()
                    .filter(order -> String.valueOf(order.getOrderID()).equals(selectedOrderID))
                    .findFirst()
                    .orElse(null);

            if (selectedOrder == null) {
                resetUI();
                return;
            }

            labelCustomerDetails.setText(selectedOrder.getCustomerID());
            labelItemsOrdered.setText(String.join(", ", selectedOrder.getItems().stream()
                    .map(MenuItem::getName)
                    .toArray(String[]::new)));
            labelOrderID.setText(String.valueOf(selectedOrder.getOrderID()));
            labelOrderStatus.setText(selectedOrder.getStatus());
            labelQuantity.setText(String.valueOf(selectedOrder.getItems().size()));
            labelTotalAmount.setText(String.format("$%.2f", selectedOrder.getTotalCost()));
        } catch (Exception e) {
            e.printStackTrace();
            resetUI();
        }
    }

    private void resetUI() {
        labelCustomerDetails.setText("N/A");
        labelItemsOrdered.setText("N/A");
        labelOrderID.setText("N/A");
        labelOrderStatus.setText("N/A");
        labelQuantity.setText("N/A");
        labelTotalAmount.setText("N/A");
    }

    private void showOrderItemsWindow(List<MenuItem> items) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderItemsView.fxml"));
        Parent root = loader.load();

        OrderItemsViewController controller = loader.getController();
        controller.setItems(items);

        Stage stage = new Stage();
        stage.setTitle("Order Items");
        stage.setScene(new javafx.scene.Scene(root, 400, 300));
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
