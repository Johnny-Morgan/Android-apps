package dev.johnmorgan.donationdataentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity implements View.OnKeyListener {

    String message = "";
    EditText brand;
    EditText description;
    EditText unitWeight;
    EditText unitsPerCase;
    EditText totalCases;
    RadioGroup units;
    boolean isMillilitres;
    boolean isGrams;


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
            message += "Brand: " + brand.getEditableText().toString()
                    + " Description: " + description.getEditableText().toString()
                    + " Unit pack weight: " + unitWeight.getEditableText().toString();

            units = findViewById(R.id.locationRadioGroup);
            int selectedButton = units.getCheckedRadioButtonId();
            onRadioButtonClicked(selectedButton);

            message += " Pack units per case: " + unitsPerCase.getEditableText().toString()
                    + " Total cases: " + totalCases.getEditableText().toString() + " ";

            double unitWeightDouble = Double.parseDouble(unitWeight.getEditableText().toString());
            int unitsPerCaseInt = Integer.parseInt(unitsPerCase.getEditableText().toString());
            int totalWeight;

            if (isGrams || isMillilitres) {
                totalWeight = (int) (Math.round((unitWeightDouble / 1000) * unitsPerCaseInt));
            } else {
                totalWeight = (int) (Math.round(unitWeightDouble * unitsPerCaseInt));
            }

            if (totalWeight == 0) { // ensure min weight is 1
                totalWeight = 1;
            }

            message += brand.getEditableText().toString() + " " + description.getEditableText().toString()
                    + " " + unitWeightDouble;

            onRadioButtonClicked(selectedButton); // append units

            message += " X " + unitsPerCaseInt
                    + " = " + totalWeight;
            Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
            intent.putExtra("details", message.toUpperCase());
            startActivity(intent);
        }
    }

    public void onRadioButtonClicked(int button) {
        switch (button) {
            case R.id.radio_kg:
                message += "kg";
                break;
            case R.id.radio_g:
                message += "g";
                isGrams = true;
                break;
            case R.id.radio_litre:
                message += "ltr";
                break;
            case R.id.radio_ml:
                message += "ml";
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
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            continueButtonClicked(v);
        }
        return false;
    }
}
