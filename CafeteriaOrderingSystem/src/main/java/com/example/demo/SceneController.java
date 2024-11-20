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

    public void switchToCafeteriaManagerViewUIViewOrders(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cafeteria-Manager-View-ViewOrders.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCafeteriaManagerViewUIProcessOrders(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Cafeteria-Manager-View-ProcessOrders.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddMenuItemView(ActionEvent event, Menu menu) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Add-Menu-Item.fxml"));
        Parent root = loader.load();
    
        // Pass the menu object to the AddMenuItemController
        AddMenuItemController controller = loader.getController();
        controller.setMenu(menu); // Inject the menu instance
    
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        stage.setTitle("Add Menu Item");
        stage.setScene(new Scene(root, 400, 300)); // Adjust dimensions as needed
        stage.showAndWait(); // Wait for the user to complete the operation
    }

    public void switchToRemoveMenuItemView(ActionEvent event, Menu menu) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Remove-Menu-Item.fxml"));
        Parent root = loader.load();
    
        // Get the controller and pass the menu instance
        RemoveMenuItemController controller = loader.getController();
        controller.setMenu(menu); // Inject the shared menu instance
    
        Stage stage = new Stage();
        stage.setTitle("Remove Menu Items");
        stage.setScene(new Scene(root, 400, 600)); // Adjust dimensions as needed
        stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        stage.showAndWait(); // Wait for user to complete the operation
    }
    
    

}
