package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void createDonation(View view) {
        EditText donator = findViewById(R.id.donatorEditText);
        EditText date = findViewById(R.id.dateEditText);
        Intent intent = new Intent(getApplicationContext(), secondActivity.class);
        intent.putExtra("donator", donator.getEditableText().toString() + " " + date.getEditableText().toString());
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
