package dev.johnmorgan.donationdataentry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("details"), Toast.LENGTH_LONG).show();
    }
}
