package dev.johnmorgan.donationdataentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ThirdActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    String message = "";
    EditText brand;
    EditText description;
    EditText unitWeight;
    EditText unitsPerCase;
    EditText totalCases;
    RadioGroup units;
    boolean isMillilitres;
    boolean isGrams;
    boolean isKilograms;
    boolean isLitres;


    public void continueButtonClicked(View view) {
        brand = findViewById(R.id.brandEditText);
        description = findViewById(R.id.descriptionEditText);
        unitWeight = findViewById(R.id.unitWeightEditText);
        unitsPerCase = findViewById(R.id.unitsPerCaseEditText);

        if (brand.getEditableText().toString().equals("") || description.getEditableText().toString().equals("")
                || unitWeight.getEditableText().toString().equals("") || unitsPerCase.getEditableText().toString().equals("") ||
                totalCases.getEditableText().toString().equals("")) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_SHORT).show();
        } else {
            units = findViewById(R.id.locationRadioGroup);
            int selectedButton = units.getCheckedRadioButtonId();
            double unitWeightDouble = Double.parseDouble(unitWeight.getEditableText().toString());
            int unitsPerCaseInt = Integer.parseInt(unitsPerCase.getEditableText().toString());
            int totalWeight;

            onRadioButtonClicked(selectedButton);
            if (isGrams || isMillilitres) {
                totalWeight = (int) (Math.round((unitWeightDouble / 1000) * unitsPerCaseInt));
            } else {
                totalWeight = (int) (Math.round(unitWeightDouble * unitsPerCaseInt));
            }

            if (totalWeight == 0) { // ensure min weight is 1
                totalWeight = 1;
            }
            int quantity = totalWeight * Integer.parseInt(totalCases.getEditableText().toString());

            message += "Quantity: " + quantity + "\n"
                    + "Size: " + totalWeight + "\n"
                    + brand.getEditableText().toString() + " " + description.getEditableText().toString()
                    + " " + unitWeightDouble;

            if (isKilograms)
                message += "kg";
            else if (isGrams)
                message += "g";
            else if (isLitres)
                message += "ltr";
            else
                message += "ml";

            message += " X " + unitsPerCaseInt
                    + " = " + totalWeight;
            
            Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
            intent.putExtra("details", message.toUpperCase());
            intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
            finish();
        }
    }

    public void onRadioButtonClicked(int button) {
        switch (button) {
            case R.id.radio_kg:
                isKilograms = true;
                break;
            case R.id.radio_g:
                isGrams = true;
                break;
            case R.id.radio_litre:
                isLitres = true;
                break;
            case R.id.radio_ml:
                isMillilitres = true;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        totalCases = findViewById(R.id.totalCasesEditText);
        totalCases.setOnKeyListener(this);
        ConstraintLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        backgroundLayout.setOnClickListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            continueButtonClicked(v);
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
