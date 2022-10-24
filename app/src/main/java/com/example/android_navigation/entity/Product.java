package com.example.android_navigation.entity;

public class Product {
    static int geradorIds = -1;

    private int id;
    private String name;
    private String price;

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
        geradorIds++;
        this.id = geradorIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Nome: " + name;
    }
}
