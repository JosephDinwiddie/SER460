package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class UnderConstructionController {

    @FXML
    private ImageView constructionImage;
    private Scene previousScene;  // Store the previous scene

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;  // Set the previous scene
    }

    @FXML
    public void initialize() {
        // Load an image from the resources folder
        Image image = new Image(getClass().getResource("/images/construction.png").toExternalForm());
        constructionImage.setImage(image);
    }

    @FXML
    private void goBack(ActionEvent event) {
        if (previousScene != null) {  // Navigate back to the previous scene if it exists
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        }
    }
}
