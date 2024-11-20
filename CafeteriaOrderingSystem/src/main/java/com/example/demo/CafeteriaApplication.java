package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CafeteriaApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeteriaApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300); // Match dimensions with FXML
        stage.setTitle("Cafeteria Login");
        stage.setScene(scene);
        stage.setResizable(false); // Prevent resizing for consistent layout
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Cafeteria Ordering System!");
        System.out.println("Valid logins are: ");
        System.out.println("Cafeteria Manager: username: manager, password: manager123");
        System.out.println("Cafeteria Operator: username: operator, password: operator123");
        System.out.println("Customer: No login required");
        System.out.println("Some Valid Card Numbers are: ");
        System.out.println("4539 1488 0343 6467");
        System.out.println("4556-7375-8689-9855");
        launch();
    }
}
