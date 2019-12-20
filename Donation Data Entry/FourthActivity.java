package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class FourthActivity extends AppCompatActivity {

    EditText frozenUntilDate;
    EditText location;

    public void addEntryButtonClicked(View view) {
        Intent intent = getIntent();
        String message = intent.getStringExtra("details");
        frozenUntilDate = findViewById(R.id.frozenEditText);
        message += " BB: " + frozenUntilDate.getEditableText().toString();
        Log.i("message", message);
        Intent intent2 = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("details", message.toUpperCase());
        startActivity(intent2);
    }

    public void onRadioButtonClicked(int button) {
        switch (button) {
            case R.id.radio_ambient:
                location = findViewById(R.id.locationEditText);
                location.setEnabled(true);
                break;
            case R.id.radio_fridge:
                break;
            case R.id.radio_freezer:
                frozenUntilDate = findViewById(R.id.frozenEditText);
                frozenUntilDate.setEnabled(true);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
    }
}
