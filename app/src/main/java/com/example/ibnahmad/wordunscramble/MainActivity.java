package com.example.ibnahmad.wordunscramble;

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
    static AssetManager assetManager;
    private static Set<String> englishWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = this.getAssets();
        scrambled_word_edit_text = findViewById(R.id.scrambled_word_edit_text);

        result_text_view = findViewById(R.id.result_text_view);
        result_button = findViewById(R.id.unscramble_word_button);
        result_button.setOnClickListener(this);
//        loadDictionary();
    }

    private static Set<String> loadDictionary() {
        Set<String> words = new HashSet<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(assetManager.open("dictionary.txt")));
            String newLine = reader.readLine();
            while (newLine != null) {
                words.add(newLine.toLowerCase());
            }

            // return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //TODO: Log the Error
                    Log.d(TAG, "Error in closing the InputReaderStream");
                }
            }
        }
        return words;
    }

    public static ArrayList<String> generateWords(String word) {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 1; i <= word.length(); i++) {
            List<String> partialResults = generatePartialWords(word, i);
            results.addAll(partialResults);
        }
        return results;
    }

    private static ArrayList<String> generatePartialWords(String word, int length) {
        ArrayList<String> partialResults = new ArrayList<>();
        ArrayList<Character> availableChars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            availableChars.add(c);
        }
        generateWordsHelper("", availableChars, length, partialResults);
        return partialResults;
    }

    private static void generateWordsHelper(String currentWord, List<Character> availableChars, int length, List<String> results) {
        if (currentWord.length() == length) {
            if (isEnglishWord(currentWord)) {
                results.add(currentWord);
            }
            return;
        }

        Set<Character> usedChars = new HashSet<>();
        for (int i = 0; i < availableChars.size(); i++) {
            char currentChar = availableChars.get(i);
            if (usedChars.contains(currentChar)) {
                continue;
            }
            usedChars.add(currentChar);

            List<Character> newAvailableChars = new ArrayList<>(availableChars);
            newAvailableChars.remove(i);
            generateWordsHelper(currentWord + currentChar, newAvailableChars, length, results);
        }
    }

    private static boolean isEnglishWord(String word) {
        if (englishWords == null) {
            englishWords = loadDictionary();
        }
        return englishWords.contains(word.toLowerCase());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.unscramble_word_button) {
            //TODO: Solve the puzzle
            if (scrambled_word_edit_text.length() == 0) {
                scrambled_word_edit_text.setError("Please enter a scrambled word.");
            } else {
                result_text_view.setText("");
                String scrambled_word = scrambled_word_edit_text.getText().toString();
                ArrayList<String> words = generateWords(scrambled_word);
                for (String word: words) {
                    result_text_view.append(word+"\n");
                }

            }


        }
    }


}