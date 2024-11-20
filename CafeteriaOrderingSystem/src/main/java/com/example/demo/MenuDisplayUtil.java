package com.example.demo;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuDisplayUtil {

    // Method to create a scrollable menu by meal type or all meals
    public static ScrollPane createScrollableMenu(Menu menu, String mealType) {
        VBox menuLayout = new VBox(10);
        menuLayout.setPadding(new Insets(10));
        Label menuTitle = new Label(mealType + " Menu:");
        List<MenuItem> items;

        // Filter items by meal type or get all items
        if (mealType.equalsIgnoreCase("All Meals")) {
            items = new ArrayList<>(menu.getMenu()); // Get all menu items
        } else {
            items = menu.filterByMealType(mealType);
        }

        // Add title and items to the layout
        menuLayout.getChildren().add(menuTitle);
        for (MenuItem item : items) {
            menuLayout.getChildren().add(new Label(item.getMealType() + " - " + item.getName() + " - $" + item.getPrice()));
        }

        ScrollPane scrollPane = new ScrollPane(menuLayout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    // Method to create a scrollable menu sorted by meal type
    public static ScrollPane createSortedMenu(Menu menu) {
        VBox menuLayout = new VBox(10);
        menuLayout.setPadding(new Insets(10));
        Label menuTitle = new Label("Menu Sorted by Meal Type:");

        // Get sorted menu items
        List<MenuItem> sortedItems = menu.getMenu().stream()
                .sorted((item1, item2) -> item1.getMealType().compareToIgnoreCase(item2.getMealType()))
                .collect(Collectors.toList());

        // Add title and sorted items to the layout
        menuLayout.getChildren().add(menuTitle);
        for (MenuItem item : sortedItems) {
            menuLayout.getChildren().add(new Label(item.getMealType() + " - " + item.getName() + " - $" + item.getPrice()));
        }

        ScrollPane scrollPane = new ScrollPane(menuLayout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    // Method to create a scrollable menu grouped by meal type
    public static ScrollPane createGroupedMenu(Menu menu) {
        VBox menuLayout = new VBox(10);
        menuLayout.setPadding(new Insets(10));
        Label menuTitle = new Label("Menu Grouped by Meal Type:");
        menuLayout.getChildren().add(menuTitle);

        // Group items by meal type
        Map<String, List<MenuItem>> groupedMenu = menu.getMenu().stream()
                .collect(Collectors.groupingBy(MenuItem::getMealType));

        // Add each group to the layout
        for (String mealType : groupedMenu.keySet()) {
            // Add group heading
            Label heading = new Label(mealType);
            heading.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
            menuLayout.getChildren().add(heading);

            // Add items in the group
            for (MenuItem item : groupedMenu.get(mealType)) {
                menuLayout.getChildren().add(new Label(item.getName() + " - $" + item.getPrice()));
            }
        }

        ScrollPane scrollPane = new ScrollPane(menuLayout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }
}
