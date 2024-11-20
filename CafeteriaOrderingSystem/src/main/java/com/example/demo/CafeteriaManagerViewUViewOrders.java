package com.example.demo;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CafeteriaManagerViewUViewOrders extends SceneController implements Initializable {

    /***********FXML DECLARATION***********/
    @FXML
    private ListView<String> listView;

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


    /***********VARIABLES***********/
    private CafeteriaOperator cafeteriaOperator = CafeteriaOperator.getInstance();

    /***********FUNCTIONS***********/
    @FXML
    public void goBack(ActionEvent event) throws IOException {
        super.switchToCafeteriaOperatorView(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*Adds the orderIDS of the current orders to the list view*/
        listView.getItems().addAll(cafeteriaOperator.getDashboard().getAllOrdersIDs());

        /*Adds a listener for whenever the user changes the selection that changes the displayed values depending on the selection*/
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
                String getSelectedItem = (selectedItems.isEmpty())?"No item selected":selectedItems.toString();
                updateUI(getSelectedItem);
            }
        });
    }

    void updateUI(String selectedElement) {
        if (selectedElement == null || selectedElement.isBlank() || selectedElement.contains("No item selected")) {
            String errorString = "No order found";
            labelCustomerDetails.setText(errorString);
            labelItemsOrdered.setText(errorString);
            labelOrderID.setText(errorString);
            labelOrderStatus.setText(errorString);
            labelQuantity.setText(errorString);
            labelTotalAmount.setText(errorString);
            return;
        }
    
        if (selectedElement.startsWith("[") && selectedElement.endsWith("]")) {
            selectedElement = selectedElement.substring(1, selectedElement.length() - 1).trim();
        }
    
        try {
            int orderId = Integer.parseInt(selectedElement);
            System.out.println("Selected Element (Order ID): " + orderId);
    
            Order selectedOrder = null;
    
            for (Order order : cafeteriaOperator.viewOrders()) {
                System.out.println("Order ID: " + order.getOrderID() + ", Items: " + order.getItems());
                if (order.getOrderID() == orderId) {
                    selectedOrder = order;
                    break;
                }
            }
    
            if (selectedOrder != null) {
                System.out.println("Selected Order: " + selectedOrder);
    
                labelCustomerDetails.setText(selectedOrder.getCustomerID());
    
                if (!selectedOrder.getItems().isEmpty()) {
                    StringBuilder itemsBuilder = new StringBuilder();
                    for (MenuItem item : selectedOrder.getItems()) {
                        System.out.println("Item: " + item.getName());
                        itemsBuilder.append(item.getName()).append(", ");
                    }
                    labelItemsOrdered.setText(itemsBuilder.substring(0, itemsBuilder.length() - 2));
                } else {
                    labelItemsOrdered.setText("No items in order");
                }
    
                labelOrderID.setText(Integer.toString(selectedOrder.getOrderID()));
                labelOrderStatus.setText(selectedOrder.getStatus());
                labelQuantity.setText(Integer.toString(selectedOrder.getItems().size()));
                labelTotalAmount.setText(String.format("$%.2f", selectedOrder.getTotalCost()));
            } else {
                throw new IllegalArgumentException("Order not found");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            labelCustomerDetails.setText("Invalid order ID format");
            labelItemsOrdered.setText("Invalid order ID format");
            labelOrderID.setText("Invalid order ID format");
            labelOrderStatus.setText("Invalid order ID format");
            labelQuantity.setText("Invalid order ID format");
            labelTotalAmount.setText("Invalid order ID format");
        }
    }    
}
