package com.example.ibnahmad.wordunscramble;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Our shuffle will be the implementation of Fisher-YAtes-Shuffle
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int NO_COUNT = 0;
    private EditText scrambled_word_edit_text;
    private Button result_button;
    private TextView result_text_view;
    AssetManager assetManager;
    private BufferedReader reader;
    private SecureRandom random;
    private Map<String, Integer> dictionaryMap;
    private StringBuilder result;
    private Set<String> englishWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new SecureRandom();
        assetManager = this.getAssets();
        dictionaryMap = new HashMap<>();
        scrambled_word_edit_text = findViewById(R.id.scrambled_word_edit_text);

        result_text_view = findViewById(R.id.result_text_view);
        result_button = findViewById(R.id.unscramble_word_button);
        result_button.setOnClickListener(this);
        result = new StringBuilder();
        loadDictionary();
    }

    private Set<String> loadDictionary() {
        Set<String> words = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetManager.open("dictionary.txt")));
            String newLine = reader.readLine();
            while (newLine != null) {
//                dictionaryMap.put(newLine, NO_COUNT);
//                newLine = reader.readLine();
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

    public ArrayList<String> generateWords(String word) {
        ArrayList<String> results = new ArrayList<>();
        generateWordsHelper("", word, results);
        return results;
    }

    private void generateWordsHelper(String prefix, String remaining, List<String> results) {
        int length = remaining.length();

        // Base case: all characters used, add the generated word to results if it is an English word
        if (length == 0) {
            if (isEnglishWord(prefix)) {
                results.add(prefix);
            }
            return;
        }

        // Recursively generate words by selecting each character as the next prefix
        for (int i = 0; i < length; i++) {
            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generateWordsHelper(newPrefix, newRemaining, results);
        }
    }

    private boolean isEnglishWord(String word) {
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