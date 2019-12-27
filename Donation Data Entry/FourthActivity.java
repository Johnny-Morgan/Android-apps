package dev.johnmorgan.donationdataentry;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FourthActivity extends AppCompatActivity {

    EditText frozenUntilDate;
    EditText location;
    EditText usebyDate;
    Date today = Calendar.getInstance().getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String formattedDate = sdf.format(today);
    boolean isFrozen;
    boolean outOfDate;
    final Calendar myCalendar = Calendar.getInstance();

    public void addEntryButtonClicked(View view) {
        Intent intent = getIntent();
        String message = intent.getStringExtra("details");
        if (isFrozen)
            message += " FROZEN ON " + formattedDate + " BB: " + frozenUntilDate.getEditableText().toString();
        if (outOfDate)
            message += " BB: " + usebyDate.getEditableText().toString();
        Log.i("message", message);
        Intent intent2 = new Intent(getApplicationContext(), SecondActivity.class);
        intent2.putExtra("details", message.toUpperCase());
        startActivity(intent2);
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
}
