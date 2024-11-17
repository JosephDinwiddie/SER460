package com.example.demo;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CafeteriaOperatorViewUViewProcess extends SceneController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button completeButton;
    @FXML
    private Button recievedButton;
    @FXML
    private Button inProgressButton;
    @FXML
    private Label orderStatus;

    private CafeteriaOperator cafeteriaOperator = CafeteriaOperator.getInstance();

    @FXML
    private ListView<String> listView;


    @FXML
    public void goBack(ActionEvent event) throws IOException {
        super.switchToCafeteriaOperatorView(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*TBD implement actual loading of orders later*/
        listView.getItems().addAll(cafeteriaOperator.getDashboard().getAllOrdersIDs());

        /*Adds a listener for whenever the user changes the selection that changes the displayed values depending on the selection*/
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
                String getSelectedItem = (selectedItems.isEmpty())?"No item selected":selectedItems.toString();
                Order selectedOrder = findOrder(getSelectedItem);
                if(selectedOrder != null){
                    orderStatus.setText(selectedOrder.getStatus());
                }

            }
        });
    }

    @FXML
    public void changeStatusToReceived(ActionEvent event){
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
        String getSelectItem = (selectedItems.isEmpty())?"Nothing selected":selectedItems.toString();
        updateUI(getSelectItem, "Received ");
    }
    @FXML
    public void changeStatusToInProgress(ActionEvent event){
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
        String getSelectItem = (selectedItems.isEmpty())?"Nothing selected":selectedItems.toString();
        updateUI(getSelectItem, "In Progress");
    }
    @FXML
    public void changeStatusToComplete(ActionEvent event){
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();
        String getSelectItem = (selectedItems.isEmpty())?"Nothing selected":selectedItems.toString();
        updateUI(getSelectItem, "Complete");
    }

    void updateUI(String selectedElement, String newStatus){
        Order selectedOrder = findOrder(selectedElement);

        if(selectedOrder != null){
            selectedOrder.setStatus(newStatus);
            orderStatus.setText(newStatus);
        }
    }

    public Order findOrder(String selectedElement){
        /* Find order based on their orderID*/
        if(selectedElement == null || selectedElement.contains("no orders")){
            String errorString = "No order found";
            orderStatus.setText(errorString);
            return null;
        }

        // Ensure the string has brackets
        if (selectedElement.startsWith("[") && selectedElement.endsWith("]")) {
            selectedElement = selectedElement.substring(1, selectedElement.length() - 1);
        }

        Order selectedOrder = null;
        for (Order order: cafeteriaOperator.viewOrders()) {
            if(order.getOrderID() == Integer.parseInt(selectedElement)){
                selectedOrder = order;
            }
        }
        return selectedOrder;
    }
}
