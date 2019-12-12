package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    String message = "";
    EditText brand;
    EditText description;
    EditText unitWeight;
    EditText unitsPerCase;
    EditText totalCases;

    public void addProduct(View view) {
        brand = findViewById(R.id.brandEditText);
        description = findViewById(R.id.descriptionEditText);
        unitWeight = findViewById(R.id.unitWeightEditText);
        unitsPerCase = findViewById(R.id.unitsPerCaseEditText);
        totalCases = findViewById(R.id.totalCasesEditText);

        message += "Brand: " + brand.getEditableText().toString()
                + " Description: " + description.getEditableText().toString()
                + " Unit pack weight " + unitWeight.getEditableText().toString()
                + " Pack units per case: " + unitsPerCase.getEditableText().toString()
                + " Total cases: " + totalCases.getEditableText().toString();
        Log.i("Details", message);

        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.putExtra("details", message);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }
}
