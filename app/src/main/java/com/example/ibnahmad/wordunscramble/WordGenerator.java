package com.example.ibnahmad.wordunscramble;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordGenerator {

    private static Context context;

    public WordGenerator(Context context){
        this.context = context;
    }

    private static final String TAG = WordGenerator.class.getSimpleName();
    private static Set<String> englishWords;

    private static Set<String> loadDictionary() {
        Set<String> words = new HashSet<>();
        AssetManager assetManager = context.getAssets();
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

}
