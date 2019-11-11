package dev.johnmorgan.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    int correctAnswers = 0;
    int totalAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView timerTextView = findViewById(R.id.timerTextView);
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText(correctAnswers + "/" + totalAnswers);

        new CountDownTimer(30_000, 1000) {
            public void onTick(long l) {
                timerTextView.setText((l / 1000) + "s");
            }

            public void onFinish() {

            }
        }.start();
    }
}
