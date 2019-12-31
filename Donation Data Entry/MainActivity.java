package dev.johnmorgan.donationdataentry;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    EditText donator;
    EditText dateEditText;
    final Calendar myCalendar = Calendar.getInstance();

    public void createDonation(View view) {
        if(donator.getEditableText().toString().equals("") || dateEditText.getEditableText().toString().equals("")){
            Toast.makeText(this, "Donator and Date are required", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("donator", donator.getEditableText().toString() + " " + dateEditText.getEditableText().toString());
            startActivity(intent);
        }
    }

    public void updateLabel(EditText editText) {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        editText.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donator = findViewById(R.id.donatorEditText);
        dateEditText = findViewById(R.id.dateEditText);
        donator.setOnKeyListener(this);
        ConstraintLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        backgroundLayout.setOnClickListener(this);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(dateEditText);
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            createDonation(v);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.backgroundLayout) { // remove keyboard when user clicks outside of keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
