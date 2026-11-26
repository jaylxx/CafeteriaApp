package com.example.cafeteriaapp.model;

public class FoodItem {

    private String name;
    private String category;
    private double price;
    private int prepTime;
    private boolean available;

    public FoodItem(String name, String category, double price, int prepTime, boolean available) {
        setName(name);
        setCategory(category);
        setPrice(price);
        setPrepTime(prepTime);
        setAvailable(available);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "ללא שם";
        } else {
            this.name = name.trim();
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            this.category = "כללי";
        } else {
            this.category = category.trim();
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        if (prepTime >= 1 && prepTime <= 60) {
            this.prepTime = prepTime;
        } else {
            this.prepTime = 5;
        }
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return name + " (" + category + ") - " + price + " ₪, " + prepTime + " דק'";
    }
}
