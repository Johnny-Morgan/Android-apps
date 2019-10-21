package dev.johnmorgan.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertCurrency(View view) {
        final double EXCHANGE_RATE = 1.3;
        EditText editText = findViewById(R.id.currencyEditText);
        double currency = Double.parseDouble(editText.getText().toString());
        double convertedCurrency = currency * EXCHANGE_RATE;
        String convertedCurrencyString = String.format("%.2f", convertedCurrency);
        Toast.makeText(this, "€" + currency + " is $" + convertedCurrencyString, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
