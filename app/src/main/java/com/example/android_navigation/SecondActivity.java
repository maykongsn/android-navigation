package com.example.android_navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_navigation.controller.Codes;

public class SecondActivity extends AppCompatActivity {
    int id;

    EditText editName;
    EditText editPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        id = -1;

        editName = findViewById(R.id.nameEditText);
        editPrice = findViewById(R.id.priceEditText);

        if (getIntent().getExtras() != null) {
            String idString = getIntent().getExtras().getString(Codes.KEY_ID);
            String name = getIntent().getExtras().getString(Codes.KEY_NAME);
            String price = getIntent().getExtras().getString(Codes.KEY_PRICE);

            if(idString != null) {
                id = Integer.parseInt(idString);
                Toast.makeText(this, "Id: " + id, Toast.LENGTH_LONG).show();
            }

            editName.setText(name);
            editPrice.setText(price);
        }
    }

    public void back(View view) {
        finish();
    }

    public void add(View view) {
        String name = editName.getText().toString();
        String price = editPrice.getText().toString();

        Intent intent = new Intent();
        intent.putExtra( Codes.KEY_NAME, name );
        intent.putExtra( Codes.KEY_PRICE, price );

        if( id >= 0 ) intent.putExtra( Codes.KEY_ID, "" + id );

        setResult( Codes.RESPONSE_OK, intent );
        finish();
    }
}