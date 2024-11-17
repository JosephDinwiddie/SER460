package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CafeteriaApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeteriaApplication.class.getResource("Main-UI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Cafeteria Ordering System");
        stage.setScene(scene);
        stage.show();

        List<MenuItem> items = List.of(
                new MenuItem(56, "Caeser Salad", "Lunch", 8.99),
                new MenuItem(55, "Chicken Sandwich", "Lunch", 9.99)
        );

        OrderManager.getInstance().placeOrder("9999", items);
    }

    public static void main(String[] args) {
        launch();
    }
}