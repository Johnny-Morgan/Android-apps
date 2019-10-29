package dev.johnmorgan.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty
    int activePlayer = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameOver = false;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (!gameOver && gamestate[tappedCounter] == 2) {
            counter.setTranslationY(-1500);
            gamestate[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
                Log.i("tag", counter.getTag().toString());
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotationBy(1800).alpha(1);
            for (int[] winningPosition : winningPositions) {
                if (gamestate[winningPosition[0]] == gamestate[winningPosition[1]] && gamestate[winningPosition[1]] == gamestate[winningPosition[2]]
                        && gamestate[winningPosition[0]] != 2) {
                    String message;
                    if (activePlayer == 1)
                        message = "Yellow";
                    else
                        message = "Red";

                    TextView winnerTextView = findViewById(R.id.winnerTextView);
                    Button playAgainButton = findViewById(R.id.playAgainButton);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setText(message + " has won!");
                    gameOver = true;
                }
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
        for (int i = 0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }

        for (int i = 0; i < grid.getChildCount(); i++) {
            ImageView counter = (ImageView) grid.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
