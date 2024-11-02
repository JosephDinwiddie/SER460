package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeteriaOperatorViewUI extends SceneController{

    @FXML
    private Button viewOrdersButton;

    @FXML
    private Button processOrderButton;

    @FXML
    private Button updateOrderStatusButton;

    private CafeteriaOperator cafeteriaOperator = new CafeteriaOperator();

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
}
