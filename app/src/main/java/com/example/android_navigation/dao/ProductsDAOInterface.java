package com.example.android_navigation.dao;

import android.content.Context;

import com.example.android_navigation.entity.Product;

import java.util.ArrayList;

public interface ProductsDAOInterface {
    static ProductsDAOInterface getInstance(Context context) {
        return null;
    }

    Product getProduct(int productId);
    ArrayList<Product> ListProducts();

    boolean addProduct(Product product);
    boolean editProduct(Product product);
    boolean deleteProduct(int productId);
}
