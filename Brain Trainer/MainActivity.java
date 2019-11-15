package dev.johnmorgan.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;
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
    List<Integer> answers = new ArrayList<>();

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
            answers.clear();
            generateQuestion();
        }
    }

    public void generateQuestion() {
        int firstNumber, secondNumber, correctAnswer, wrongAnswer, bound;
        int operation = r.nextInt(3); // 0 for addition, 1 for multiplication, 2 subtraction
        choice1 = findViewById(R.id.choice1TextView);
        choice2 = findViewById(R.id.choice2TextView);
        choice3 = findViewById(R.id.choice3TextView);
        choice4 = findViewById(R.id.choice4TextView);

        if (operation == 0) { // addition question
            firstNumber = r.nextInt(51);
            secondNumber = r.nextInt(51);
            correctAnswer = firstNumber + secondNumber;
            bound = 111;
            questionTextView.setText("" + firstNumber + " + " + secondNumber);
        } else if (operation == 1) { // multiplication question
            firstNumber = r.nextInt(11);
            secondNumber = r.nextInt(11);
            correctAnswer = firstNumber * secondNumber;
            bound = 111;
            questionTextView.setText("" + firstNumber + " x " + secondNumber);
        } else { // subtraction question
            firstNumber = r.nextInt(31);
            secondNumber = r.nextInt(31);
            while (secondNumber > firstNumber)
                secondNumber = r.nextInt(11);
            correctAnswer = firstNumber - secondNumber;
            bound = 31;
            questionTextView.setText("" + firstNumber + " - " + secondNumber);
        }
        for (int i = 0; i < 4; i++) {
            if (i == correctAnswerGrid) {
                answers.add(correctAnswer);
            } else {
                wrongAnswer = r.nextInt(bound);
                while (wrongAnswer == correctAnswer || answers.contains(wrongAnswer)) { // prevent duplicate answers
                    wrongAnswer = r.nextInt(bound);
                }
                answers.add(wrongAnswer);
            }
        }
        choice1.setText(Integer.toString(answers.get(0)));
        choice2.setText(Integer.toString(answers.get(1)));
        choice3.setText(Integer.toString(answers.get(2)));
        choice4.setText(Integer.toString(answers.get(3)));
        answers.clear(); // to prevent next game from having previous question's answers
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
            CountDownTimer cdt = new CountDownTimer(60_000, 1000) {
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

    public void playAgain(View view) {
        correctAnswers = 0;
        totalAnswers = 0;
        startTraining();
        generateQuestion();
    }

    public void start(View view) {
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
