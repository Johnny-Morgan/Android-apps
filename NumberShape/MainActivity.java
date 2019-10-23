package dev.johnmorgan.numbershapes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static boolean checkIfSquare(int num) {
        return (Math.floor(Math.sqrt(num)) == Math.sqrt(num));
    }

    public static boolean checkIfTriangular(int num) {
        int x = 1;
        int triangularNumber = 0;
        while (triangularNumber < num) {
            triangularNumber += x;
            x++;
        }
        return num == triangularNumber;
    }

    public void checkNumber(View view) {
        EditText editText = findViewById(R.id.numberEditText);
        int num = Integer.parseInt(editText.getEditableText().toString());
        String message = "";
        if (checkIfSquare(num) && checkIfTriangular(num)) {
            message = " is a triangular and a square number";
        } else if (checkIfTriangular(num)) {
            message = " is a triangular number";
        } else if (checkIfSquare(num)) {
            message = " is a square number";
        } else {
            message = " is neither a triangular nor a square number";
        }

        Toast.makeText(this, num + message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
