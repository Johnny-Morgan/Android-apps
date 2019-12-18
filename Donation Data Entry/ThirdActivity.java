package dev.johnmorgan.donationdataentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    String message = "";
    EditText brand;
    EditText description;
    EditText unitWeight;
    EditText unitsPerCase;
    EditText totalCases;
    RadioGroup units;

    public void addProduct(View view) {
        brand = findViewById(R.id.brandEditText);
        description = findViewById(R.id.descriptionEditText);
        unitWeight = findViewById(R.id.unitWeightEditText);
        unitsPerCase = findViewById(R.id.unitsPerCaseEditText);
        totalCases = findViewById(R.id.totalCasesEditText);

        message += "Brand: " + brand.getEditableText().toString()
                + " Description: " + description.getEditableText().toString()
                + " Unit pack weight: " + unitWeight.getEditableText().toString();

        units = findViewById(R.id.unitsRadioGroup);
        int selectedButton = units.getCheckedRadioButtonId();
        onRadioButtonClicked(selectedButton);

        message += " Pack units per case: " + unitsPerCase.getEditableText().toString()
                + " Total cases: " + totalCases.getEditableText().toString() + " ";

        double unitWeightDouble = Double.parseDouble(unitWeight.getEditableText().toString());
        int unitsPerCaseInt = Integer.parseInt(unitsPerCase.getEditableText().toString());
        int totalWeight = (int) (Math.round(unitWeightDouble * unitsPerCaseInt));

        message += brand.getEditableText().toString() + " " + description.getEditableText().toString()
                + " " + unitWeightDouble;

        onRadioButtonClicked(selectedButton); // append units
        
        message += " X " + unitsPerCaseInt
                + " = " + totalWeight;

        Intent intent = new Intent(getApplicationContext(), FourthActivity.class);
        intent.putExtra("details", message);
        startActivity(intent);
    }

    public void onRadioButtonClicked(int button) {
        switch (button) {
            case R.id.radio_kg:
                message += "kg";
                break;
            case R.id.radio_g:
                message += "g";
                break;
            case R.id.radio_litre:
                message += "ltr";
                break;
            case R.id.radio_ml:
                message += "ml";
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }
}
