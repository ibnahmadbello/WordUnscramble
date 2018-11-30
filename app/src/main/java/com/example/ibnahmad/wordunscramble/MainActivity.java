package com.example.ibnahmad.wordunscramble;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.SecureRandom;

// Our shuffle will be the implementation of Fisher-YAtes-Shuffle
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText scrambled_word_edit_text;
    private Button result_button;
    private RecyclerView recyclerView;
    AssetManager assetManager;
    private BufferedReader reader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SecureRandom random = new SecureRandom();
        assetManager = this.getAssets();
        scrambled_word_edit_text = findViewById(R.id.scrambled_word_edit_text);

        result_button = findViewById(R.id.unscramble_word_button);
        result_button.setOnClickListener(this);


    }

    private static int calculateFactorial(int length){
        if (length <= 1){
            return 1;
        } else {
            return length * calculateFactorial(length -1);
        }
    }

    private void loadDictionary(){

        String text = "";

        try {
            /*InputStream inputStream = assetManager.open("dictionary.txt");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            text = new String(buffer);
            // TextView.setText(text);*/
            reader = new BufferedReader(new InputStreamReader(assetManager.open("dictionary.txt")));
            StringBuilder stringBuilder = new StringBuilder();
            String newLine = reader.readLine();
            while (newLine != null){
                stringBuilder.append(newLine);
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
                String scrambled_word = scrambled_word_edit_text.getText().toString();
                int scrambled_word_length = scrambled_word.length();
                calculateFactorial(scrambled_word_length);
            }


        }
    }

}