package dev.johnmorgan.donationdataentry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
                + " Total cases: " + totalCases.getEditableText().toString();

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("details", message);
        startActivity(intent);
    }

    public void onRadioButtonClicked(int button) {
        switch (button) {
            case 2131165290:
                message += "kg";
                break;
            case 2131165289:
                message += "g";
                break;
            case 2131165291:
                message += "ltr";
                break;
            case 2131165292:
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
