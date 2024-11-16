package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {

    private int menuID;
    private String mealType;
    private Collection<MenuItem> menu;

    public Menu() {
        menu = new ArrayList<>();
        initializeMenu(); // Add predetermined examples
    }

    private void initializeMenu() {
        // Adding predetermined breakfast items
        menu.add(new MenuItem(1, "Pancakes", "Breakfast", 5.99));
        menu.add(new MenuItem(2, "Omelette", "Breakfast", 6.99));

        // Adding predetermined lunch items
        menu.add(new MenuItem(3, "Caesar Salad", "Lunch", 8.99));
        menu.add(new MenuItem(4, "Chicken Sandwich", "Lunch", 9.99));

        // Adding predetermined dinner items
        menu.add(new MenuItem(5, "Grilled Salmon", "Dinner", 15.99));
        menu.add(new MenuItem(6, "Steak", "Dinner", 19.99));
    }

    public void displayItems() {
        System.out.println("Menu Items:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public List<MenuItem> filterByMealType(String mealType) {
        return menu.stream()
                .filter(item -> item.getMealType().equalsIgnoreCase(mealType))
                .collect(Collectors.toList());
    }

    // Getters and setters
    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public Collection<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(Collection<MenuItem> menu) {
        this.menu = menu;
    }
}

class MenuItem {
    private int id;
    private String name;
    private String mealType;
    private double price;

    public MenuItem(int id, String name, String mealType, double price) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.price = price;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mealType='" + mealType + '\'' +
                ", price=" + price +
                '}';
    }
}
