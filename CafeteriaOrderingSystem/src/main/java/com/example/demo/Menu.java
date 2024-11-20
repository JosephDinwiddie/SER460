package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Menu class represents the cafeteria's menu and uses the Singleton pattern
 * to ensure that a single instance is shared across the application.
 */
public class Menu {

    // Singleton instance of the Menu
    private static Menu instance;

    // Collection to hold menu items
    private Collection<MenuItem> menu;

    /**
     * Private constructor to prevent external instantiation.
     * Initializes the menu with default items.
     */
    private Menu() {
        menu = new ArrayList<>();
        initializeMenu(); // Initialize menu with some default items
    }

    /**
     * Static method to retrieve the single instance of Menu.
     * Ensures that only one instance exists at any time.
     *
     * @return The singleton instance of Menu.
     */
    public static synchronized Menu getInstance() {
        if (instance == null) {
            instance = new Menu(); // Create the instance if it doesn't exist
        }
        return instance;
    }

    /**
     * Initializes the menu with some default items.
     * This is called only once when the Menu is created.
     */
    private void initializeMenu() {
        menu.add(new MenuItem(1, "Pancakes", "Breakfast", 5.99));
        menu.add(new MenuItem(2, "Omelette", "Breakfast", 6.99));
        menu.add(new MenuItem(3, "Caesar Salad", "Lunch", 8.99));
        menu.add(new MenuItem(4, "Chicken Sandwich", "Lunch", 9.99));
        menu.add(new MenuItem(5, "Grilled Salmon", "Dinner", 15.99));
        menu.add(new MenuItem(6, "Steak", "Dinner", 19.99));
    }

    /**
     * Adds a new menu item to the menu.
     *
     * @param item The MenuItem to be added.
     */
    public void addMenuItem(MenuItem item) {
        menu.add(item);
        System.out.println("Menu item added: " + item);
    }

    /**
     * Removes a menu item by its ID.
     *
     * @param id The ID of the MenuItem to remove.
     */
    public void removeMenuItem(int id) {
        boolean removed = menu.removeIf(item -> item.getId() == id);
        if (removed) {
            System.out.println("Menu item with ID " + id + " removed.");
        } else {
            System.out.println("No menu item found with ID " + id);
        }
    }

    /**
     * Filters menu items by meal type.
     *
     * @param mealType The meal type to filter by (e.g., Breakfast, Lunch, Dinner).
     * @return A list of MenuItems matching the given meal type.
     */
    public List<MenuItem> filterByMealType(String mealType) {
        return menu.stream()
                .filter(item -> item.getMealType().equalsIgnoreCase(mealType))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all menu items.
     *
     * @return A collection of all MenuItems in the menu.
     */
    public Collection<MenuItem> getMenu() {
        return menu;
    }

    /**
     * Prints all menu items to the console for debugging purposes.
     */
    public void displayItems() {
        System.out.println("Current Menu Items:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }
}
