package com.example.android_navigation.dao;

import android.content.Context;

import com.example.android_navigation.entity.Product;

import java.util.ArrayList;

public class ProductsDAO implements ProductsDAOInterface{
    private static ArrayList<Product> products = new ArrayList<>();
    private static Context context;

    private static ProductsDAO productsDAO = null;

    private ProductsDAO(Context context) {
        ProductsDAO.context = context;
    }

    public static ProductsDAOInterface getInstance(Context context) {
        if(productsDAO == null) {
            productsDAO = new ProductsDAO(context);
        }
        return productsDAO;
    }

    @Override
    public Product getProduct(int productId) {
        Product productSearch = null;
        for(Product product : products) {
            if(product.getId() == productId) {
                productSearch = product;
            }
        }
        return productSearch;
    }

    @Override
    public ArrayList<Product> ListProducts() {
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        products.add(product);
        return true;
    }

    @Override
    public boolean editProduct(Product product) {
        for(Product newProduct : products) {
            if(newProduct.getId() == product.getId()) {
                newProduct.setName(product.getName());
                newProduct.setPrice(product.getPrice());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        for(Product product : products) {
            if(product.getId() == productId) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }
}

