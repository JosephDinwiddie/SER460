package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class OrderItemsViewController {

    @FXML
    private VBox itemsContainer;

    /**
     * Populates the items in the scrollable container.
     *
     * @param items The list of items to display.
     */
    public void setItems(List<MenuItem> items) {
        itemsContainer.getChildren().clear();
        for (MenuItem item : items) {
            Label itemLabel = new Label(item.getName() + " - $" + item.getPrice());
            itemsContainer.getChildren().add(itemLabel);
        }
    }

    /**
     * Closes the pop-up window.
     */
    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) itemsContainer.getScene().getWindow();
        stage.close();
    }
}
