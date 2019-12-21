package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FourthActivity extends AppCompatActivity {

    EditText frozenUntilDate;
    EditText location;
    Date today = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String formattedDate = sdf.format(today);

    public void addEntryButtonClicked(View view) {
        Intent intent = getIntent();
        String message = intent.getStringExtra("details");
        frozenUntilDate = findViewById(R.id.frozenEditText);
        message += " FROZEN ON " + formattedDate + " BB: " + frozenUntilDate.getEditableText().toString();
        Log.i("message", message);
        Intent intent2 = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("details", message.toUpperCase());
        startActivity(intent2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        frozenUntilDate = findViewById(R.id.frozenEditText);
        location = findViewById(R.id.locationEditText);
        RadioGroup rg = findViewById(R.id.locationRadioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_freezer) {
                    frozenUntilDate.setEnabled(true);
                    location.setEnabled(false);
                } else if (checkedId == R.id.radio_ambient) {
                    location.setEnabled(true);
                    frozenUntilDate.setEnabled(false);
                } else {
                    frozenUntilDate.setEnabled(false);
                    location.setEnabled(false);
                }
            }
        });
    }
}
