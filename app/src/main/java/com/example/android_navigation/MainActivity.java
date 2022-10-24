package com.example.android_navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_navigation.controller.Codes;
import com.example.android_navigation.dao.ProductsDAO;
import com.example.android_navigation.dao.ProductsDAOInterface;
import com.example.android_navigation.entity.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ProductsDAOInterface productsDAO;
    CustomAdapter adapter;
    ArrayList<Product> products;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsDAO = ProductsDAO.getInstance(this);
        products = productsDAO.ListProducts();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        adapter = new CustomAdapter(this, products);
        RecyclerView recyclerView = findViewById(R.id.productsRecyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        editText = findViewById(R.id.editText);
    }

    public void addProducts(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, Codes.REQUEST_ADD);
    }

    public void removeProduct(int position) {
        productsDAO.deleteProduct(position);
        adapter.notifyDataSetChanged();
    }

    public void editProducts(View view) {
        int id = Integer.parseInt(editText.getText().toString());
        Product product = productsDAO.getProduct(id);

        String name = product.getName();
        String price = product.getPrice();

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(Codes.KEY_ID, ""+id);
        intent.putExtra(Codes.KEY_NAME, name);
        intent.putExtra(Codes.KEY_PRICE, price);

        startActivityForResult(intent, Codes.REQUEST_EDT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Codes.REQUEST_ADD && requestCode == Codes.RESPONSE_OK) {
            String name =  data.getExtras().getString(Codes.KEY_NAME);
            String price = data.getExtras().getString(Codes.KEY_PRICE);

            Product product = new Product(name, price);
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();

            productsDAO.addProduct(product);
            adapter.notifyDataSetChanged();
        } else if (requestCode == Codes.REQUEST_EDT && resultCode == Codes.RESPONSE_OK){
            String idString = data.getExtras().getString(Codes.KEY_ID);
            String name = data.getExtras().getString( Codes.KEY_NAME);
            String price = data.getExtras().getString( Codes.KEY_PRICE);

            if (idString != null){
                int id = Integer.parseInt(idString);

                Product product = new Product(name, price);
                product.setId(id);

                productsDAO.editProduct(product);
                adapter.notifyDataSetChanged();
            }

            adapter.notifyDataSetChanged();
        }
    }
}