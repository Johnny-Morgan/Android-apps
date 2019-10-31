package dev.johnmorgan.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameOver = false;
    static int counterCount;

    public void dropIn(View view) {
        counterCount++;
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        Button playAgainButton = findViewById(R.id.playAgainButton);

        if (!gameOver && gameState[tappedCounter] == 2) {
            counter.setTranslationY(-1500);
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            
            counter.animate().translationYBy(1500).rotationBy(1800).alpha(1);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    String message;
                    if (activePlayer == 1)
                        message = "Yellow";
                    else
                        message = "Red";

                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText(message + " has won!");
                    gameOver = true;
                    counterCount = 0;
                }
            }

            // Check for drawn game
            if (counterCount == 9 && !gameOver) {
                winnerTextView.setVisibility(View.VISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setText("Draw!");
                gameOver = true;
                counterCount = 0;
            }
        }
    }

    public void playAgain(View view) {
        GridLayout grid = findViewById(R.id.gridLayout);
        TextView winnerTextView = findViewById(R.id.winnerTextView);
        Button playAgainButton = findViewById(R.id.playAgainButton);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        gameOver = false;
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setAlpha(0.0f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
