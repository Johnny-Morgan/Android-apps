package dev.johnmorgan.guessinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int randomNumber;

    public void generateRandomNumber(){
        Random r = new Random();
        randomNumber = r.nextInt(100) + 1;
    }
    public void userGuess(View view) {

        EditText editText = findViewById(R.id.guessEditText);
        int guess = Integer.parseInt(editText.getEditableText().toString());

        String message;

        if (guess == randomNumber) {
            message = "You got it! Try again!";
            generateRandomNumber();
        } else if (guess < randomNumber) {
            message = "Higher!";
        } else {
            message = "Lower";
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRandomNumber();
    }
}
