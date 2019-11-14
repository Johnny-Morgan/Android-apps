package dev.johnmorgan.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int correctAnswers = 0;
    int totalAnswers = 0;
    TextView timerTextView;
    TextView questionTextView;
    TextView choice1;
    TextView choice2;
    TextView choice3;
    TextView choice4;
    TextView resultTextView;
    boolean timerOn;
    Random r = new Random();
    int correctAnswerGrid = r.nextInt(4);
    TextView scoreTextView;


    public void answerQuestion(View view) {
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        if (timerOn) {
            TextView answer = (TextView) view;
            int tappedAnswer = Integer.parseInt(answer.getTag().toString());
            if (tappedAnswer == correctAnswerGrid) {
                correctAnswers++;
                resultTextView.setText("Correct!");
                totalAnswers++;
            } else {
                resultTextView.setText("Wrong :(");
                totalAnswers++;
            }

            scoreTextView.setText(correctAnswers + "/" + totalAnswers);
            correctAnswerGrid = r.nextInt(4);
            generateQuestion();
        }
    }

    public void generateQuestion() {
        int firstNumber, secondNumber, correctAnswer;
        int operation = r.nextInt(2); // 0 for addition, 1 for multiplication
        choice1 = findViewById(R.id.choice1TextView);
        choice2 = findViewById(R.id.choice2TextView);
        choice3 = findViewById(R.id.choice3TextView);
        choice4 = findViewById(R.id.choice4TextView);

        if (operation == 0) {
            firstNumber = r.nextInt(30);
            secondNumber = r.nextInt(30);
            correctAnswer = firstNumber + secondNumber;
            questionTextView.setText("" + firstNumber + " + " + secondNumber);
        } else {
            firstNumber = r.nextInt(10);
            secondNumber = r.nextInt(10);
            correctAnswer = firstNumber * secondNumber;
            questionTextView.setText("" + firstNumber + " x " + secondNumber);
        }

        if (correctAnswerGrid == 0) {
            choice1.setText("" + correctAnswer);
            choice2.setText("" + r.nextInt(60));
            choice3.setText("" + r.nextInt(60));
            choice4.setText("" + r.nextInt(60));
        } else if (correctAnswerGrid == 1) {
            choice1.setText("" + r.nextInt(60));
            choice2.setText("" + correctAnswer);
            choice3.setText("" + r.nextInt(60));
            choice4.setText("" + r.nextInt(60));
        } else if (correctAnswerGrid == 2) {
            choice1.setText("" + r.nextInt(60));
            choice2.setText("" + r.nextInt(60));
            choice3.setText("" + correctAnswer);
            choice4.setText("" + r.nextInt(60));
        } else {
            choice1.setText("" + r.nextInt(60));
            choice2.setText("" + r.nextInt(60));
            choice3.setText("" + r.nextInt(60));
            choice4.setText("" + correctAnswer);
        }
    }

    public void startTraining() {
        if (!timerOn) {
            timerOn = true;

            timerTextView = findViewById(R.id.timerTextView);
            questionTextView = findViewById(R.id.questionTextView);
            scoreTextView = findViewById(R.id.scoreTextView);
            scoreTextView.setText("0/0");
            final Button playAgainButton = findViewById(R.id.playAgainButton);
            resultTextView = findViewById(R.id.resultTextView);
            resultTextView.setText("");
            playAgainButton.setVisibility(View.INVISIBLE);
            CountDownTimer cdt = new CountDownTimer(15_000, 1000) {
                public void onTick(long l) {
                    timerTextView.setText((l / 1000) + "s");
                }

                public void onFinish() {
                    timerOn = false;
                    resultTextView.setText("Game Over!");
                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }.start();
        }
    }

    public void playAgain(View view){
        correctAnswers = 0;
        totalAnswers = 0;
        startTraining();
        generateQuestion();
    }

    public void start(View view){
        ConstraintLayout gameLayout = findViewById(R.id.gameLayout);
        Button startButton = findViewById(R.id.startButton);
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playAgain(questionTextView); // no particular reason for choosing questionTextView
    }
}
