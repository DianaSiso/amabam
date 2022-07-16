package com.example.amabam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.amabam.R;

public class categories_hangman extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_hangman);
    }

    @Override
    public void onBackPressed() {
        // disable back button
    }

    public void category_selected(View view) {
        Log.d("DEBUG_CAT", getResources().getResourceName(view.getId()));
        Intent intent = new Intent(this, hangman_game.class);
        intent.putExtra("category", getResources().getResourceName(view.getId()));
        startActivity(intent);
    }

    public void click_to_home(View view) {
        Intent intent = new Intent(this, home_games.class);
        startActivity(intent);
    }
}