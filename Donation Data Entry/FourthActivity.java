package dev.johnmorgan.donationdataentry;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FourthActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    EditText frozenUntilDate;
    EditText location;
    EditText usebyDate;
    Date today = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = sdf.format(today);
    boolean isFrozen;
    boolean outOfDate;
    final Calendar myCalendar = Calendar.getInstance();
    RadioButton frozenRadioButton;
    RadioButton ambientRadioButton;
    RadioButton fridgeRadioButton;

    public void addEntryButtonClicked(View view) {
        if (usebyDate.getEditableText().toString().equals("")) {
            Toast.makeText(this, "Use by date must be filled", Toast.LENGTH_SHORT).show();
        } else if (frozenRadioButton.isChecked() && frozenUntilDate.getEditableText().toString().equals("")) {
            Toast.makeText(this, "Frozen until date must be filled", Toast.LENGTH_SHORT).show();
        } else if (ambientRadioButton.isChecked() && location.getEditableText().toString().equals("")) {
            Toast.makeText(this, "Location must be filled", Toast.LENGTH_SHORT).show();
        } else if (!ambientRadioButton.isChecked() && !fridgeRadioButton.isChecked() && !frozenRadioButton.isChecked()) {
            Toast.makeText(this, "Location Type must be filled", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = getIntent();
            String message = intent.getStringExtra("details");
            if (isFrozen) {
                message += " FROZEN ON " + formattedDate + " BB: " + frozenUntilDate.getEditableText().toString();
            }
            else if (outOfDate) {
                String[] dateParts = formattedDate.split("/");
                String day = dateParts[0];
                String month = dateParts[1];
                String year = "2099";
                message += " BB: " + usebyDate.getEditableText().toString()
                        + "\nUse By: " + day + "/" + month + "/" + year;
            }
            else {
                message += "\nUse By: " + usebyDate.getEditableText().toString();
            }
            Log.i("message", message);
            Intent intent2 = new Intent(getApplicationContext(), SecondActivity.class);
            intent2.putExtra("details", message.toUpperCase());
            startActivity(intent2);
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
        setContentView(R.layout.activity_fourth);
        frozenUntilDate = findViewById(R.id.frozenEditText);
        usebyDate = findViewById(R.id.usebyEditText);
        location = findViewById(R.id.locationEditText);
        RadioGroup rg = findViewById(R.id.locationRadioGroup);
        location.setOnKeyListener(this);
        ConstraintLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        backgroundLayout.setOnClickListener(this);
        frozenRadioButton = findViewById(R.id.radio_freezer);
        ambientRadioButton = findViewById(R.id.radio_ambient);
        fridgeRadioButton = findViewById(R.id.radio_fridge);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final Date d;
                Date d1;
                if (checkedId == R.id.radio_freezer) {
                    frozenUntilDate.setEnabled(true);
                    location.setEnabled(false);
                    isFrozen = true;
                } else if (checkedId == R.id.radio_ambient) {
                    location.setEnabled(true);
                    frozenUntilDate.setEnabled(false);
                    try {
                        d1 = sdf.parse(usebyDate.getEditableText().toString());
                    } catch (Exception e) {
                        d1 = today;
                    }
                    d = d1;
                    if (d.before(today)) {
                        outOfDate = true;
                    } else {
                        outOfDate = false;
                    }
                } else {
                    frozenUntilDate.setEnabled(false);
                    location.setEnabled(false);
                }
            }
        });
        datePicker(usebyDate);
        datePicker(frozenUntilDate);
    }

    public void datePicker(final EditText editText) {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(editText);
            }
        };

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FourthActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            addEntryButtonClicked(v);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.backgroundLayout) { // remove keyboard when user clicks outside of keyboard
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
