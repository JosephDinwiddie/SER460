package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuSelectionController {

    private Scene previousScene;

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
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
    private void goBack(ActionEvent event) {
        if (previousScene != null) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        }
    }

    @FXML
    private void openBreakfastMenu(ActionEvent event) {
        // Load and display the breakfast menu
        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void openLunchMenu(ActionEvent event) {
        // Load and display the lunch menu
        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private void openDinnerMenu(ActionEvent event) {
        // Load and display the dinner menu
        try {
            showUnderConstructionPage(event);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

