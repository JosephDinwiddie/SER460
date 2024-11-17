package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToCafeteriaManagerView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cafeteria-Manager-View.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCafeteriaOperatorView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cafeteria-Operator-View.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCustomerView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Customer-View.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainUI(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Main-UI.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCafeteriaOperatorViewUIViewOrders(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cafeteria-Operator-View-ViewOrders.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCafeteriaOperatorViewUIProcessOrders(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cafeteria-Operator-View-ProcessOrders.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
