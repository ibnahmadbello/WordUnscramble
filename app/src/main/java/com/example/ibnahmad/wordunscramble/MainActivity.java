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
import java.util.HashMap;
import java.util.Map;

// Our shuffle will be the implementation of Fisher-YAtes-Shuffle
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

    private static int calculateFactorial(int length){
        if (length <= 1){
            return 1;
        } else {
            return length * calculateFactorial(length -1);
        }
    }

    private void loadDictionary(){

        try {
            /*InputStream inputStream = assetManager.open("dictionary.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            text = new String(buffer);
            // TextView.setText(text);*/
            reader = new BufferedReader(new InputStreamReader(assetManager.open("dictionary.txt")));
            String newLine = reader.readLine();
            while (newLine != null){
                dictionaryMap.put(newLine, NO_COUNT);
                newLine = reader.readLine();
            }

            // return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e){
                    //TODO: Log the Error
                    Log.d(TAG, "Error in closing the InputReaderStream");
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.unscramble_word_button){
            //TODO: Solve the puzzle
            if (scrambled_word_edit_text.length() == 0){
                scrambled_word_edit_text.setError("Please enter a scrambled word.");
            } else {
                result_text_view.setText(null);
                String scrambled_word = scrambled_word_edit_text.getText().toString();
                int scrambled_word_length = scrambled_word.length();
                for (int i = 0; i < calculateFactorial(scrambled_word_length); i++){
                    String solved_word = shuffle(random, scrambled_word);
                    if (dictionaryMap.containsKey(solved_word.toUpperCase())){
                        result.append(solved_word).append("\n");
                    }
                }

                result_text_view.setText(result);
                result = new StringBuilder();
            }


        }
    }

    private static String shuffle(SecureRandom secureRandom, String inputString){

        // Convert your string into simple char array
        char a[] = inputString.toCharArray();

        // Scramble the letters using the standard Fisher-Yates shuffle
        for (int i = 0; i < a.length; i++){
//        for (int i = a.length; i > 0; i--) { // Was done using reverse mechanism to improve performance

            int j = secureRandom.nextInt(a.length);

            // swap letters
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        return new String(a);
    }

}