package dev.johnmorgan.donationdataentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    List<String> products = new ArrayList<>();
    ListView productListView;
    ArrayAdapter<String> arrayAdapter;

    public void addEntry(View view) {
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivity(intent);
    }

    public void addProduct() {
        productListView = findViewById(R.id.detailsListView);
        Intent intent2 = getIntent();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        productListView.setAdapter(arrayAdapter);

        if (intent2.getStringExtra("details") != null) {
            products.add(intent2.getStringExtra("details"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView);
        textView.setText(intent.getStringExtra("donator"));
        addProduct();
    }
}
