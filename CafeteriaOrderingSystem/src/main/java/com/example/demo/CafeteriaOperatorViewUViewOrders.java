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

public class CafeteriaOperatorViewUViewOrders extends SceneController implements Initializable {

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

    void updateUI(String selectedElement){
        /* Find order based on their orderID*/
        if(selectedElement == null || selectedElement.contains("no orders")){
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
            selectedElement = selectedElement.substring(1, selectedElement.length() - 1);
        }

        Order selectedOrder = null;
        for (Order order: cafeteriaOperator.viewOrders()) {
            if(order.getOrderID() == Integer.parseInt(selectedElement)){
                selectedOrder = order;
            }
        }

        if(selectedOrder != null){

            labelCustomerDetails.setText(selectedOrder.getCustomerID());

            String itemsString;
            if(!selectedOrder.getItems().isEmpty()){
                StringBuilder builder = new StringBuilder();
                for (MenuItem item : selectedOrder.getItems()) {
                    builder.append(item.getName()).append(" ");
                }
                itemsString = builder.toString().trim();
            }else{
                itemsString = "No items in order";
            }

            labelItemsOrdered.setText(itemsString);

            labelOrderID.setText(Integer.toString(selectedOrder.getOrderID()));

            labelOrderStatus.setText(selectedOrder.getStatus());

            labelQuantity.setText(Integer.toString(selectedOrder.getItems().size()));

            labelTotalAmount.setText(Double.toString(selectedOrder.getTotalCost()));
        }
    }
}
