package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public void addEntry(View view) {
        Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.textView);
        textView.setText(intent.getStringExtra("donator"));
    }
}