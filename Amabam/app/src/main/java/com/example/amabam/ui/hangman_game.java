
package com.example.amabam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amabam.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class hangman_game extends AppCompatActivity {
    String word = "";
    int tries = 5;
    int mistakes = 5;
    ArrayList<ImageView> hearts = new ArrayList<>();
    ArrayList<Button> buttons_word = new ArrayList<>();
    String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangman_game);

        ArrayList<String> categories = new ArrayList<>();

        // init buttons word
        Button char_1 = findViewById(R.id.char_1);
        Button char_2 = findViewById(R.id.char_2);
        Button char_3 = findViewById(R.id.char_3);
        Button char_4 = findViewById(R.id.char_4);
        Button char_5 = findViewById(R.id.char_5);
        buttons_word.add(char_1); buttons_word.add(char_2); buttons_word.add(char_3); buttons_word.add(char_4); buttons_word.add(char_5);

        // init hearts
        ImageView heart_1 = findViewById(R.id.heart_1);
        ImageView heart_2 = findViewById(R.id.heart_2);
        ImageView heart_3 = findViewById(R.id.heart_3);
        ImageView heart_4 = findViewById(R.id.heart_4);
        ImageView heart_5 = findViewById(R.id.heart_5);
        hearts.add(heart_1); hearts.add(heart_2); hearts.add(heart_3); hearts.add(heart_4); hearts.add(heart_5);

        // init words arrays
        ArrayList<String> animals = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> flowers = new ArrayList<>();
        ArrayList<String> fruits = new ArrayList<>();
        ArrayList<String> countries = new ArrayList<>();
        animals.add("ZEBRA"); animals.add("VIPER"); animals.add("TIGER"); animals.add("WHALE"); animals.add("MOUSE"); animals.add("EAGLE"); animals.add("RAVEN"); animals.add("SPRAT"); animals.add("SNAKE");
        fruits.add("APPLE"); fruits.add("MANGO"); fruits.add("GUAVA"); fruits.add("MELON"); fruits.add("BERRY"); fruits.add("ACKEE"); fruits.add("LEMON"); fruits.add("PEACH"); fruits.add("PRUNE"); fruits.add("PAPAW");
        flowers.add("DAISY"); flowers.add("LILAC"); flowers.add("TULIP"); flowers.add("LOTUS"); flowers.add("PANSY"); flowers.add("PAGLE"); flowers.add("OXLIP"); flowers.add("VINCA"); flowers.add("PHLOX"); flowers.add("PEONY");
        colors.add("TOPAZ"); colors.add("SEPIA"); colors.add("GREEN"); colors.add("WHITE"); colors.add("BLACK"); colors.add("PEARL"); colors.add("LILAC"); colors.add("AZURE"); colors.add("AMBER"); colors.add("CORAL");
        countries.add("CHILE"); countries.add("JAPAN"); countries.add("MALTA"); countries.add("SPAIN"); countries.add("NEPAL"); countries.add("QATAR"); countries.add("ITALY"); countries.add("CHINA"); countries.add("HAITI"); countries.add("INDIA");


        // init categories
        categories.add("animals");
        categories.add("flowers");
        categories.add("fruits");
        categories.add("colors");
        categories.add("countries");

        // get word idx
        Random rand = new Random();
        int word_idx = rand.nextInt(10);

        // know which category user decided
        category = getIntent().getExtras().getString("category");
        Log.d("DEBUG_HANGMAN category", category);

        if (category.contains("animals")) {
            word = animals.get(word_idx);
        } else if (category.contains("flowers")) {
            word = flowers.get(word_idx);
        } else if (category.contains("fruits")) {
            word = fruits.get(word_idx);
        } else if (category.contains("colors")) {
            word = colors.get(word_idx);
        } else if (category.contains("countries")) {
            word = countries.get(word_idx);
        } else { // random
            // get category idx
            int category_idx = rand.nextInt(5);
            category = categories.get(category_idx);
            if (category.contains("animals")) {
                word = animals.get(word_idx);
            } else if (category.contains("flowers")) {
                word = flowers.get(word_idx);
            } else if (category.contains("fruits")) {
                word = fruits.get(word_idx);
            } else if (category.contains("colors")) {
                word = colors.get(word_idx);
            } else if (category.contains("countries")) {
                word = countries.get(word_idx);
            }
        }

        // create_word_space(word);

    }

    private void create_word_space(String word) {
        Button char_1 = findViewById(R.id.char_1);
        Button char_2 = findViewById(R.id.char_2);
        Button char_3 = findViewById(R.id.char_3);
        Button char_4 = findViewById(R.id.char_4);
        Button char_5 = findViewById(R.id.char_5);

        char_1.setText(String.valueOf(word.charAt(0)));
        char_2.setText(String.valueOf(word.charAt(1)));
        char_3.setText(String.valueOf(word.charAt(2)));
        char_4.setText(String.valueOf(word.charAt(3)));
        char_5.setText(String.valueOf(word.charAt(4)));
    }

    public void send_char(View view) {
        Button button = (Button) view;
        String guess = button.getText().toString();
        if (!word.contains(guess)) {
            button.setClickable(false);
            button.setBackgroundColor(Color.parseColor("#505050"));
            mistakes = mistakes - 1;
            ImageView heart_to_change = hearts.get(mistakes);
            heart_to_change.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        } else {
            tries = tries - 1;
            for (int i=0; i<word.length(); i++) {
                if (guess.equals(String.valueOf(word.charAt(i)))) {
                    Button button_char = buttons_word.get(i);
                    button_char.setText(guess);
                }
            }
        }


        if (mistakes == 0 || tries == 0) {
            //all buttons disable
            LinearLayout first_layout = findViewById(R.id.first_line);
            LinearLayout second_layout = findViewById(R.id.second_line);
            LinearLayout third_layout = findViewById(R.id.third_line);
            LinearLayout fourth_layout = findViewById(R.id.fourth_line);
            int count_first = first_layout.getChildCount();
            int count_second = second_layout.getChildCount();
            int count_third = third_layout.getChildCount();
            int count_fourth = fourth_layout.getChildCount();
            View button_to_change = null;
            for(int i=0; i<count_first; i++) {
                button_to_change = first_layout.getChildAt(i);
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
            }
            for(int i=0; i<count_second; i++) {
                button_to_change = second_layout.getChildAt(i);
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
            }
            for(int i=0; i<count_third; i++) {
                button_to_change = third_layout.getChildAt(i);
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
            }
            for(int i=0; i<count_fourth; i++) {
                button_to_change = fourth_layout.getChildAt(i);
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
            }

            TextView game_over = findViewById(R.id.game_over_text);
            if (tries == 0) {
                game_over.setText("CONGRATULATIONS");
            } else {
                for (int i=0; i<word.length(); i++) {
                    Button button_char = buttons_word.get(i);
                    button_char.setText(String.valueOf(word.charAt(i)));
                }
            }
            game_over.setVisibility(View.VISIBLE);
            Button hangman_reset = findViewById(R.id.hangman_reset);
            hangman_reset.setVisibility(View.VISIBLE);
            Button hangman_save = findViewById(R.id.hangman_save);
            hangman_save.setVisibility(View.VISIBLE);
        }
    }

    public void reset_button(View view) {
        Intent intent = new Intent(this, hangman_game.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}