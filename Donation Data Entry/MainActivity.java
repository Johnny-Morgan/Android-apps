package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void createDonation(View view) {
        EditText donator = findViewById(R.id.donatorEditText);
        EditText date = findViewById(R.id.dateEditText);
        Log.i("Donator", donator.getEditableText().toString());
        Log.i("Date", date.getEditableText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
