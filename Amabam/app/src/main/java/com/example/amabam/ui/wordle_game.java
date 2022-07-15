package com.example.amabam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amabam.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class wordle_game extends AppCompatActivity {

    String word = "";
    String[] words_list = null;
    int tries = -1;
    ArrayList<LinearLayout> lines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordle_game);
        words_to_list();
        word = pick_word();

        // init layouts
        LinearLayout first_line = findViewById(R.id.first_line_wordle);
        LinearLayout second_line = findViewById(R.id.second_line_wordle);
        LinearLayout third_line = findViewById(R.id.third_line_wordle);
        LinearLayout fourth_line = findViewById(R.id.fourth_line_wordle);
        LinearLayout fifth_line = findViewById(R.id.fifth_line_wordle);
        LinearLayout sixth_line = findViewById(R.id.sixth_line_wordle);
        lines.add(first_line); lines.add(second_line); lines.add(third_line); lines.add(fourth_line); lines.add(fifth_line); lines.add(sixth_line);

        Log.d("DEBUG_WORDLE word", word);
    }

    public void send_char(View view) {
        TextView guess = findViewById(R.id.wordle_guess);
        String guess_text = guess.getText().toString();
        String charButton = ((Button) view).getText().toString();
        String guess_updated = guess_text + charButton;
        if (guess_updated.length() <= 5) {
            guess.setText(guess_updated);
        } else {
            guess.setText(guess_updated.substring(1));
        }
    }

    public void enter_word(View view) {
        TextView guess = findViewById(R.id.wordle_guess);
        String guess_text = guess.getText().toString();
        TextView error = findViewById(R.id.wordle_error);
        if (guess_text.length() != 5) {
            error.setVisibility(View.VISIBLE);
        } else {
            error.setVisibility(View.INVISIBLE);
            check_word(guess_text);
        }

        // TODO: verificar que o numero de tentativas for 6 ent game over
    }

    private void check_word(String guess) {
        int correct_letters = 0;
        tries = tries + 1;
        LinearLayout line_to_change = lines.get(tries);
        for (int y=0; y<5; y++) {
            Button button_to_change = (Button) line_to_change.getChildAt(y);
            button_to_change.setText(String.valueOf(guess.charAt(y)));
            // check if letter_word == letter_guess
            if (word.charAt(y) == guess.charAt(y)) {
                correct_letters = correct_letters + 1;
                button_to_change.setBackgroundColor(Color.parseColor("#44bb5e"));
            } else {
                // check if word contains the letter, if not, disable key
                if (!word.contains(String.valueOf(guess.charAt(y)))) {
                    disable_key(String.valueOf(guess.charAt(y)));
                    // TODO: não está a desativar as teclas
                    // ERROR
                    button_to_change.setBackgroundColor(Color.parseColor("#ef5757"));
                } else {
                    button_to_change.setBackgroundColor(Color.parseColor("#facd7b"));
                }
            }


        }
        // TODO: verificar que o numero de letras corretas é menor que 5, se não, win
    }

    private void disable_key(String letter) {
        LinearLayout first_layout = findViewById(R.id.first_line_wordle);
        LinearLayout second_layout = findViewById(R.id.second_line_wordle);
        LinearLayout third_layout = findViewById(R.id.third_line_wordle);
        LinearLayout fourth_layout = findViewById(R.id.fourth_line_wordle);
        int count_first = first_layout.getChildCount();
        int count_second = second_layout.getChildCount();
        int count_third = third_layout.getChildCount();
        int count_fourth = fourth_layout.getChildCount();
        View button_to_change = null;
        for(int i=0; i<count_first; i++) {
            button_to_change = first_layout.getChildAt(i);
            Button button_to_change_text = (Button) button_to_change;
            if (button_to_change_text.getText().toString().equals(letter)) {
                Log.d("DEBUG_WORDLE equals", letter);
                button_to_change_text.setClickable(false);
                button_to_change_text.setBackgroundColor(Color.parseColor("#505050"));
                return;
            }
        }
        for(int i=0; i<count_second; i++) {
            button_to_change = second_layout.getChildAt(i);
            Button button_to_change_text = (Button) button_to_change;
            if (button_to_change_text.getText().toString().equals(letter)) {
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
                return;
            }
        }
        for(int i=0; i<count_third; i++) {
            button_to_change = third_layout.getChildAt(i);
            Button button_to_change_text = (Button) button_to_change;
            if (button_to_change_text.getText().toString().equals(letter)) {
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
                return;
            }
        }
        for(int i=0; i<count_fourth; i++) {
            button_to_change = fourth_layout.getChildAt(i);
            Button button_to_change_text = (Button) button_to_change;
            if (button_to_change_text.getText().toString().equals(letter)) {
                button_to_change.setClickable(false);
                button_to_change.setBackgroundColor(Color.parseColor("#505050"));
                return;
            }
        }
    }

    public void delete_button(View view) {
        TextView guess = findViewById(R.id.wordle_guess);
        String guess_text = guess.getText().toString();
        if (guess_text.length() != 0) {
            String guess_updated = guess_text.substring(0, guess_text.length()-1);
            guess.setText(guess_updated);
        }

    }

    private void words_to_list() {
        String words = "Abuse\n" +
                "Adult\n" +
                "Agent\n" +
                "Anger\n" +
                "Apple\n" +
                "Award\n" +
                "Basis\n" +
                "Beach\n" +
                "Birth\n" +
                "Block\n" +
                "Blood\n" +
                "Board\n" +
                "Brain\n" +
                "Bread\n" +
                "Break\n" +
                "Brown\n" +
                "Buyer\n" +
                "Cause\n" +
                "Chain\n" +
                "Chair\n" +
                "Chest\n" +
                "Chief\n" +
                "Child\n" +
                "China\n" +
                "Claim\n" +
                "Class\n" +
                "Clock\n" +
                "Coach\n" +
                "Coast\n" +
                "Court\n" +
                "Cover\n" +
                "Cream\n" +
                "Crime\n" +
                "Cross\n" +
                "Crowd\n" +
                "Crown\n" +
                "Cycle\n" +
                "Dance\n" +
                "Death\n" +
                "Depth\n" +
                "Doubt\n" +
                "Draft\n" +
                "Drama\n" +
                "Dream\n" +
                "Dress\n" +
                "Drink\n" +
                "Drive\n" +
                "Earth\n" +
                "Enemy\n" +
                "Entry\n" +
                "Error\n" +
                "Event\n" +
                "Faith\n" +
                "Fault\n" +
                "Field\n" +
                "Fight\n" +
                "Final\n" +
                "Floor\n" +
                "Focus\n" +
                "Force\n" +
                "Frame\n" +
                "Frank\n" +
                "Front\n" +
                "Fruit\n" +
                "Glass\n" +
                "Grant\n" +
                "Grass\n" +
                "Green\n" +
                "Group\n" +
                "Guide\n" +
                "Heart\n" +
                "Henry\n" +
                "Horse\n" +
                "Hotel\n" +
                "House\n" +
                "Image\n" +
                "Index\n" +
                "Input\n" +
                "Issue\n" +
                "Japan\n" +
                "Jones\n" +
                "Judge\n" +
                "Knife\n" +
                "Laura\n" +
                "Layer\n" +
                "Level\n" +
                "Lewis\n" +
                "Light\n" +
                "Limit\n" +
                "Lunch\n" +
                "Major\n" +
                "March\n" +
                "Match\n" +
                "Metal\n" +
                "Model\n" +
                "Money\n" +
                "Month\n" +
                "Motor\n" +
                "Mouth\n" +
                "Music\n" +
                "Night\n" +
                "Noise\n" +
                "North\n" +
                "Novel\n" +
                "Nurse\n" +
                "Offer\n" +
                "Order\n" +
                "Other\n" +
                "Owner\n" +
                "Panel\n" +
                "Paper\n" +
                "Party\n" +
                "Peace\n" +
                "Peter\n" +
                "Phase\n" +
                "Phone\n" +
                "Piece\n" +
                "Pilot\n" +
                "Pitch\n" +
                "Place\n" +
                "Plane\n" +
                "Plant\n" +
                "Plate\n" +
                "Point\n" +
                "Pound\n" +
                "Power\n" +
                "Press\n" +
                "Price\n" +
                "Pride\n" +
                "Prize\n" +
                "Proof\n" +
                "Queen\n" +
                "Radio\n" +
                "Range\n" +
                "Ratio\n" +
                "Reply\n" +
                "Right\n" +
                "River\n" +
                "Round\n" +
                "Route\n" +
                "Rugby\n" +
                "Scale\n" +
                "Scene\n" +
                "Scope\n" +
                "Score\n" +
                "Sense\n" +
                "Shape\n" +
                "Share\n" +
                "Sheep\n" +
                "Sheet\n" +
                "Shift\n" +
                "Shirt\n" +
                "Shock\n" +
                "Sight\n" +
                "Simon\n" +
                "Skill\n" +
                "Sleep\n" +
                "Smile\n" +
                "Smith\n" +
                "Smoke\n" +
                "Sound\n" +
                "South\n" +
                "Space\n" +
                "Speed\n" +
                "Spite\n" +
                "Sport\n" +
                "Squad\n" +
                "Staff\n" +
                "Stage\n" +
                "Start\n" +
                "State\n" +
                "Steam\n" +
                "Steel\n" +
                "Stock\n" +
                "Stone\n" +
                "Store\n" +
                "Study\n" +
                "Stuff\n" +
                "Style\n" +
                "Sugar\n" +
                "Table\n" +
                "Taste\n" +
                "Terry\n" +
                "Theme\n" +
                "Thing\n" +
                "Title\n" +
                "Total\n" +
                "Touch\n" +
                "Tower\n" +
                "Track\n" +
                "Trade\n" +
                "Train\n" +
                "Trend\n" +
                "Trial\n" +
                "Trust\n" +
                "Truth\n" +
                "Uncle\n" +
                "Union\n" +
                "Unity\n" +
                "Value\n" +
                "Video\n" +
                "Visit\n" +
                "Voice\n" +
                "Waste\n" +
                "Watch\n" +
                "Water\n" +
                "While\n" +
                "White\n" +
                "Whole\n" +
                "Woman\n" +
                "World\n" +
                "Youth\n" +
                "Alcon\n" +
                "Aught\n" +
                "Hella\n" +
                "Ought\n" +
                "Thame\n" +
                "There\n" +
                "Thine\n" +
                "Thine\n" +
                "Where\n" +
                "Which\n" +
                "Whose\n" +
                "Whoso\n" +
                "Yours\n" +
                "Admit\n" +
                "Adopt\n" +
                "Agree\n" +
                "Allow\n" +
                "Alter\n" +
                "Apply\n" +
                "Argue\n" +
                "Arise\n" +
                "Avoid\n" +
                "Begin\n" +
                "Blame\n" +
                "Break\n" +
                "Bring\n" +
                "Build\n" +
                "Burst\n" +
                "Carry\n" +
                "Catch\n" +
                "Cause\n" +
                "Check\n" +
                "Claim\n" +
                "Clean\n" +
                "Clear\n" +
                "Climb\n" +
                "Close\n" +
                "Count\n" +
                "Cover\n" +
                "Cross\n" +
                "Dance\n" +
                "Doubt\n" +
                "Drink\n" +
                "Drive\n" +
                "Enjoy\n" +
                "Enter\n" +
                "Exist\n" +
                "Fight\n" +
                "Focus\n" +
                "Force\n" +
                "Guess\n" +
                "Imply\n" +
                "Issue\n" +
                "Judge\n" +
                "Laugh\n" +
                "Learn\n" +
                "Leave\n" +
                "Let’s\n" +
                "Limit\n" +
                "Marry\n" +
                "Match\n" +
                "Occur\n" +
                "Offer\n" +
                "Order\n" +
                "Phone\n" +
                "Place\n" +
                "Point\n" +
                "Press\n" +
                "Prove\n" +
                "Raise\n" +
                "Reach\n" +
                "Refer\n" +
                "Relax\n" +
                "Serve\n" +
                "Shall\n" +
                "Share\n" +
                "Shift\n" +
                "Shoot\n" +
                "Sleep\n" +
                "Solve\n" +
                "Sound\n" +
                "Speak\n" +
                "Spend\n" +
                "Split\n" +
                "Stand\n" +
                "Start\n" +
                "State\n" +
                "Stick\n" +
                "Study\n" +
                "Teach\n" +
                "Thank\n" +
                "Think\n" +
                "Throw\n" +
                "Touch\n" +
                "Train\n" +
                "Treat\n" +
                "Trust\n" +
                "Visit\n" +
                "Voice\n" +
                "Waste\n" +
                "Watch\n" +
                "Worry\n" +
                "Would\n" +
                "Write\n" +
                "Above\n" +
                "Acute\n" +
                "Alive\n" +
                "Alone\n" +
                "Angry\n" +
                "Aware\n" +
                "Awful\n" +
                "Basic\n" +
                "Black\n" +
                "Blind\n" +
                "Brave\n" +
                "Brief\n" +
                "Broad\n" +
                "Brown\n" +
                "Cheap\n" +
                "Chief\n" +
                "Civil\n" +
                "Clean\n" +
                "Clear\n" +
                "Close\n" +
                "Crazy\n" +
                "Daily\n" +
                "Dirty\n" +
                "Early\n" +
                "Empty\n" +
                "Equal\n" +
                "Exact\n" +
                "Extra\n" +
                "Faint\n" +
                "False\n" +
                "Fifth\n" +
                "Final\n" +
                "First\n" +
                "Fresh\n" +
                "Front\n" +
                "Funny\n" +
                "Giant\n" +
                "Grand\n" +
                "Great\n" +
                "Green\n" +
                "Gross\n" +
                "Happy\n" +
                "Harsh\n" +
                "Heavy\n" +
                "Human\n" +
                "Ideal\n" +
                "Inner\n" +
                "Joint\n" +
                "Large\n" +
                "Legal\n" +
                "Level\n" +
                "Light\n" +
                "Local\n" +
                "Loose\n" +
                "Lucky\n" +
                "Magic\n" +
                "Major\n" +
                "Minor\n" +
                "Moral\n" +
                "Naked\n" +
                "Nasty\n" +
                "Naval\n" +
                "Other\n" +
                "Outer\n" +
                "Plain\n" +
                "Prime\n" +
                "Prior\n" +
                "Proud\n" +
                "Quick\n" +
                "Quiet\n" +
                "Rapid\n" +
                "Ready\n" +
                "Right\n" +
                "Roman\n" +
                "Rough\n" +
                "Round\n" +
                "Royal\n" +
                "Rural\n" +
                "Sharp\n" +
                "Sheer\n" +
                "Short\n" +
                "Silly\n" +
                "Sixth\n" +
                "Small\n" +
                "Smart\n" +
                "Solid\n" +
                "Sorry\n" +
                "Spare\n" +
                "Steep\n" +
                "Still\n" +
                "Super\n" +
                "Sweet\n" +
                "Thick\n" +
                "Third\n" +
                "Tight\n" +
                "Total\n" +
                "Tough\n" +
                "Upper\n" +
                "Upset\n" +
                "Urban\n" +
                "Usual\n" +
                "Vague\n" +
                "Valid\n" +
                "Vital\n" +
                "White\n" +
                "Whole\n" +
                "Wrong\n" +
                "Young\n" +
                "Afore\n" +
                "After\n" +
                "Bothe\n" +
                "Other\n" +
                "Since\n" +
                "Slash\n" +
                "Until\n" +
                "Where\n" +
                "While\n" +
                "Aback\n" +
                "Abaft\n" +
                "Aboon\n" +
                "About\n" +
                "Above\n" +
                "Accel\n" +
                "Adown\n" +
                "Afoot\n" +
                "Afore\n" +
                "Afoul\n" +
                "After\n" +
                "Again\n" +
                "Agape\n" +
                "Agogo\n" +
                "Agone\n" +
                "Ahead\n" +
                "Ahull\n" +
                "Alife\n" +
                "Alike\n" +
                "Aline\n" +
                "Aloft\n" +
                "Alone\n" +
                "Along\n" +
                "Aloof\n" +
                "Aloud\n" +
                "Amiss\n" +
                "Amply\n" +
                "Amuck\n" +
                "Apace\n" +
                "Apart\n" +
                "Aptly\n" +
                "Arear\n" +
                "Aside\n" +
                "Askew\n" +
                "Awful\n" +
                "Badly\n" +
                "Bally\n" +
                "Below\n" +
                "Canny\n" +
                "Cheap\n" +
                "Clean\n" +
                "Clear\n" +
                "Coyly\n" +
                "Daily\n" +
                "Dimly\n" +
                "Dirty\n" +
                "Ditto\n" +
                "Drily\n" +
                "Dryly\n" +
                "Dully\n" +
                "Early\n" +
                "Extra\n" +
                "False\n" +
                "Fatly\n" +
                "Feyly\n" +
                "First\n" +
                "Fitly\n" +
                "Forte\n" +
                "Forth\n" +
                "Fresh\n" +
                "Fully\n" +
                "Funny\n" +
                "Gaily\n" +
                "Gayly\n" +
                "Godly\n" +
                "Great\n" +
                "Haply\n" +
                "Heavy\n" +
                "Hella\n" +
                "Hence\n" +
                "Hotly\n" +
                "Icily\n" +
                "Infra\n" +
                "Intl.\n" +
                "Jildi\n" +
                "Jolly\n" +
                "Laxly\n" +
                "Lento\n" +
                "Light\n" +
                "Lowly\n" +
                "Madly\n" +
                "Maybe\n" +
                "Never\n" +
                "Newly\n" +
                "Nobly\n" +
                "Oddly\n" +
                "Often\n" +
                "Other\n" +
                "Ought\n" +
                "Party\n" +
                "Piano\n" +
                "Plain\n" +
                "Plonk\n" +
                "Plumb\n" +
                "Prior\n" +
                "Queer\n" +
                "Quick\n" +
                "Quite\n" +
                "Ramen\n" +
                "Rapid\n" +
                "Redly\n" +
                "Right\n" +
                "Rough\n" +
                "Round\n" +
                "Sadly\n" +
                "Secus\n" +
                "Selly\n" +
                "Sharp\n" +
                "Sheer\n" +
                "Shily\n" +
                "Short\n" +
                "Shyly\n" +
                "Silly\n" +
                "Since\n" +
                "Sleek\n" +
                "Slyly\n" +
                "Small\n" +
                "So-So\n" +
                "Sound\n" +
                "Spang\n" +
                "Srsly\n" +
                "Stark\n" +
                "Still\n" +
                "Stone\n" +
                "Stour\n" +
                "Super\n" +
                "Tally\n" +
                "Tanto\n" +
                "There\n" +
                "Thick\n" +
                "Tight\n" +
                "Today\n" +
                "Tomoz\n" +
                "Truly\n" +
                "Twice\n" +
                "Under\n" +
                "Utter\n" +
                "Verry\n" +
                "Wanly\n" +
                "Wetly\n" +
                "Where\n" +
                "Wrong\n" +
                "Wryly\n" +
                "Abaft\n" +
                "Aboon\n" +
                "About\n" +
                "Above\n" +
                "Adown\n" +
                "Afore\n" +
                "After\n" +
                "Along\n" +
                "Aloof\n" +
                "Among\n" +
                "Below\n" +
                "Circa\n" +
                "Cross\n" +
                "Furth\n" +
                "Minus\n" +
                "Neath\n" +
                "Round\n" +
                "Since\n" +
                "Spite\n" +
                "Under\n" +
                "Until";
        words_list = words.toUpperCase().split("\n");
    }

    private String pick_word() {
        Random rand = new Random();
        int idx = rand.nextInt(words_list.length);
        return words_list[idx];
    }
}