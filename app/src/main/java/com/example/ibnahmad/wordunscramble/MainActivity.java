package com.example.ibnahmad.wordunscramble;

import static com.example.ibnahmad.wordunscramble.WordGenerator.generateWords;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText scrambled_word_edit_text;
    private Button result_button;
    private TextView result_text_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrambled_word_edit_text = findViewById(R.id.scrambled_word_edit_text);

        result_text_view = findViewById(R.id.result_text_view);
        result_button = findViewById(R.id.unscramble_word_button);
        result_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String result = "";
        if (view.getId() == R.id.unscramble_word_button) {
            //TODO: Solve the puzzle
            if (scrambled_word_edit_text.length() == 0) {
                scrambled_word_edit_text.setError("Please enter a scrambled word.");
            } else {
                String scrambled_word = scrambled_word_edit_text.getText().toString();
                ArrayList<String> words = generateWords(scrambled_word);
                for (String word: words) {
                    result += word;
                    result += "\n";
                }
            }
        }
        result_text_view.setText(result);
    }
}