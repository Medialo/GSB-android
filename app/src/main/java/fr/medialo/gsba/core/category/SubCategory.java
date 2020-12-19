package fr.medialo.gsba.core.category;

import androidx.annotation.NonNull;

public class SubCategory {

    private int id;
    private String name;
    private double cost;
    private Category category;

    public SubCategory(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public SubCategory(int id, String name, double cost, Category category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.category = category;
    }

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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name;
    }
}
