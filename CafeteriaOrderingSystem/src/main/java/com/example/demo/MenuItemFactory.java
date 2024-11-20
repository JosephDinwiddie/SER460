package com.example.demo;

public class MenuItemFactory {

    // Factory method to create a new menu item
    public static MenuItem createMenuItem(int id, String name, String mealType, double price) {
        return new MenuItem(id, name, mealType, price);
    }
}
