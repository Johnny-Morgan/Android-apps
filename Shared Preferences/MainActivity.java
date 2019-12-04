package dev.johnmorgan.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("dev.johnmorgan.sharedpreferences", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("username", "Johnny").apply();

        String username = sharedPreferences.getString("username", "");

        Log.i("Username", username);

        ArrayList<String> friends = new ArrayList<>();
        friends.add("Jack");
        friends.add("Hurley");
        friends.add("Kate");
        friends.add("Sawyer");
        friends.add("John");

        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
