package dev.johnmorgan.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekBar;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    Button button;
    boolean timerOn = false;

    public String calculateTime(long time) {
        long minutes, seconds;
        if (time < 60) {
            minutes = 0;
            seconds = time;
        } else {
            minutes = time / 60;
            seconds = time % 60;
        }
        return String.format("%d:%02d", minutes, seconds);
    }

    public void resetTimer() {
        seekBar.setEnabled(true);
        // reset timer to 30 seconds
        seekBar.setProgress(30);
        textView.setText(calculateTime(seekBar.getProgress()));
        button.setText("GO!");
        timerOn = false;
    }
    public void startTimer(View view) {
        if (!timerOn) {
            button = findViewById(R.id.button);
            button.setText("STOP!");
            timerOn = true;
            long time = seekBar.getProgress() * 1000 + 100;
            seekBar.setEnabled(false);

            countDownTimer = new CountDownTimer(time, 1000) {
                public void onTick(long l) {
                    textView.setText(calculateTime(l / 1000));
                }

                public void onFinish() {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();

        } else {
            countDownTimer.cancel();
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.timerTextView);
        seekBar = findViewById(R.id.timerSeekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        textView.setText(calculateTime(seekBar.getProgress()));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(calculateTime(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
