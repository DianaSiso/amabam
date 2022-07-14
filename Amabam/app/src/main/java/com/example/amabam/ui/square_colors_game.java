package com.example.amabam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.amabam.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class square_colors_game extends AppCompatActivity {

    ArrayList<Button> buttons_list = new ArrayList<>();
    HashMap<Integer, String[]> colors_dict = new HashMap<>();
    int chosen_button;
    SeekBar timer_bar;
    TextView timer_text;
    TextView punctuation_text;
    CountDownTimer count_down_timer;
    Button square_reset;
    Button square_save;
    Button button_1;
    Button button_2;
    Button button_3;
    Button button_4;
    Button button_5;
    Button button_6;
    Button button_7;
    Button button_8;
    Button button_9;
    Button button_10;
    Button button_11;
    Button button_12;
    Button button_13;
    Button button_14;
    Button button_15;
    Button button_16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_colors_game);

        // init punctuation
        punctuation_text = findViewById(R.id.punctuation_text);

        // init timer
        timer_bar = findViewById(R.id.timer_bar);
        timer_text = findViewById(R.id.timer_text);
        timer_bar.setMax(120);
        timer_bar.setProgress(61);
        timer_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        count_down_timer = new CountDownTimer(timer_bar.getProgress() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                update((int) (l / 1000));
            }

            @Override
            public void onFinish() {
                game_finished();
            }
        }.start();

        // init buttons
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_10 = findViewById(R.id.button_10);
        button_11 = findViewById(R.id.button_11);
        button_12 = findViewById(R.id.button_12);
        button_13 = findViewById(R.id.button_13);
        button_14 = findViewById(R.id.button_14);
        button_15 = findViewById(R.id.button_15);
        button_16 = findViewById(R.id.button_16);
        buttons_list.add(button_1);
        buttons_list.add(button_2);
        buttons_list.add(button_3);
        buttons_list.add(button_4);
        buttons_list.add(button_5);
        buttons_list.add(button_6);
        buttons_list.add(button_7);
        buttons_list.add(button_8);
        buttons_list.add(button_9);
        buttons_list.add(button_10);
        buttons_list.add(button_11);
        buttons_list.add(button_12);
        buttons_list.add(button_13);
        buttons_list.add(button_14);
        buttons_list.add(button_15);
        buttons_list.add(button_16);

        // init colors_dict
        colors_dict.put(1, new String[]{"#8c9bf6", "#7285f9"});
        colors_dict.put(2, new String[]{"#182eb0", "#16299c"});
        colors_dict.put(3, new String[]{"#555f99", "#4a569a"});
        colors_dict.put(4, new String[]{"#0a6b6d", "#14797c"});
        colors_dict.put(5, new String[]{"#203ce4", "#102cd7"});
        colors_dict.put(6, new String[]{"#0b494b", "#024344"});
        colors_dict.put(7, new String[]{"#08888c", "#40898b"});
        colors_dict.put(8, new String[]{"#1a979b", "#11a1a5"});
        colors_dict.put(9, new String[]{"#10c3c8", "#50c4c7"});
        colors_dict.put(10, new String[]{"#8dd3d5", "#9fdddf"});
        colors_dict.put(11, new String[]{"#87eaed", "#6af2f6"});
        colors_dict.put(12, new String[]{"#15ebf1", "#00f8ff"});
        colors_dict.put(13, new String[]{"#00ffb4", "#12f1af"});
        colors_dict.put(14, new String[]{"#0ce732", "#03f92d"});
        colors_dict.put(15, new String[]{"#27d544", "#4dd063"});
        colors_dict.put(16, new String[]{"#acf47e", "#9edf73"});
        colors_dict.put(17, new String[]{"#30640d", "#235901"});
        colors_dict.put(18, new String[]{"#adbd18", "#b5c808"});
        colors_dict.put(19, new String[]{"#f2d836", "#ffdb00"});
        colors_dict.put(20, new String[]{"#ffa92c", "#ff9a0a"});

        chosen_button = putColors(colors_dict, buttons_list);
    }

    public void confirmColor(View view) {
        if (view.getId() == buttons_list.get(chosen_button).getId()) {
            // change colors
            chosen_button =  putColors(colors_dict, buttons_list);
            // add 5 seconds to time
            timer_bar.setProgress(timer_bar.getProgress() + 5);
            // add 10 points to punctuation
            int punctuation = Integer.parseInt(String.valueOf(punctuation_text.getText()));
            punctuation_text.setText(String.valueOf(punctuation + 10));
            // reset coundowntimer
            count_down_timer.cancel();
            count_down_timer = new CountDownTimer(timer_bar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    update((int) (l / 1000));
                }

                @Override
                public void onFinish() {
                    game_finished();
                }
            }.start();
        } else {
            // remove 2 seconds from time
            timer_bar.setProgress(timer_bar.getProgress() - 2);
            // remove 5 points from punctuation
            int punctuation = Integer.parseInt(String.valueOf(punctuation_text.getText()));
            punctuation_text.setText(String.valueOf(punctuation - 5));
            // reset coundowntimer
            count_down_timer.cancel();
            count_down_timer = new CountDownTimer(timer_bar.getProgress() * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    update((int) (l / 1000));
                }

                @Override
                public void onFinish() {
                    game_finished();
                }
            }.start();
        }
    }

    public int putColors(HashMap<Integer, String[]> colors_dict, ArrayList<Button> buttons_list){
        Random rand = new Random();

        // decide which button will get a different color
        int random_id = rand.nextInt(16);

        // decide which set will use
        int random_set = rand.nextInt(20) + 1;

        // decide which color will be different
        String idx_dif = colors_dict.get(random_set)[0];
        String idx_all = colors_dict.get(random_set)[1];
        int random_color = rand.nextInt(2);
        if (random_color == 1) {
            idx_dif = colors_dict.get(random_set)[1];
            idx_all = colors_dict.get(random_set)[0];
        }

        // put colors
        for (int i=0; i<16; i++) {
            if (i != random_id) {
                buttons_list.get(i).setBackgroundColor(Color.parseColor(idx_all));
            } else {
                buttons_list.get(i).setBackgroundColor(Color.parseColor(idx_dif));
            }
        }

        return random_id;
    }

    private void update(int progress) {
        int minutes = progress / 60;
        int seconds = progress % 60;
        String seconds_final = "";
        if (seconds <= 9) {
            seconds_final = "0" + seconds;
        } else {
            seconds_final = "" + seconds;
        }
        timer_bar.setProgress(progress);
        timer_text.setText(minutes + ":" + seconds_final);
    }

    private void game_finished() {
        // deactivate buttons
        for (int i=0; i<16; i++) {
            buttons_list.get(i).setBackgroundColor(Color.parseColor("#505050"));
            buttons_list.get(i).setClickable(false);
        }
        // visibility of save and reset buttons
        square_reset = findViewById(R.id.square_reset);
        square_reset.setVisibility(View.VISIBLE);
        square_save = findViewById(R.id.square_save);
        square_save.setVisibility(View.VISIBLE);
    }

    public void reset_button(View view) {
        Intent intent = new Intent(this, square_colors_game.class);
        startActivity(intent);
    }
}