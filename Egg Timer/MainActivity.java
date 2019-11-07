package dev.johnmorgan.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String calculateTime(int time){
        int minutes, seconds;
        if (time < 60){
            minutes = 0;
            seconds = time;
        } else {
            minutes = time / 60;
            seconds = time % 60;
        }
        return String.format("%d:%02d", minutes, seconds);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.timerTextView);
        SeekBar seekBar = findViewById(R.id.timerSeekBar);
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
