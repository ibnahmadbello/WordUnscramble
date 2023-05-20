//package com.example.ibnahmad.wordunscramble;
//
//import java.security.SecureRandom;
//
//public class Dump {
//
//    private static String shuffle(SecureRandom secureRandom, String inputString) {
//
//        // Convert your string into simple char array
//        char a[] = inputString.toCharArray();
//
//        // Scramble the letters using the standard Fisher-Yates shuffle
//        for (int i = 0; i < a.length; i++) {
////        for (int i = a.length; i > 0; i--) { // Was done using reverse mechanism to improve performance
//
//            int j = secureRandom.nextInt(a.length);
//
//            // swap letters
//            char temp = a[i];
//            a[i] = a[j];
//            a[j] = temp;
//        }
//
//        return new String(a);
//    }
//
//
//    private static String threeShuffle(SecureRandom secureRandom, String inputString) {
//
//        // Convert your string into simple char array
//        char a[] = inputString.toCharArray();
//
//        // Scramble the letters using the standard Fisher-Yates shuffle
//        for (int i = 0; i < 3; i++) {
////        for (int i = a.length; i > 0; i--) { // Was done using reverse mechanism to improve performance
//
//            int j = secureRandom.nextInt(a.length);
//
//            // swap letters
//            char temp = a[i];
//            a[i] = a[j];
//            a[j] = temp;
//        }
//
//        return new String(a);
//    }
//
//    private static String fourShuffle(SecureRandom secureRandom, String inputString) {
//
//        // Convert your string into simple char array
//        char a[] = inputString.toCharArray();
//
//        // Scramble the letters using the standard Fisher-Yates shuffle
//        for (int i = 0; i < a.length; i++) {
////        for (int i = a.length; i > 0; i--) { // Was done using reverse mechanism to improve performance
//
//            int j = secureRandom.nextInt(a.length);
//
//            // swap letters
//            char temp = a[i];
//            a[i] = a[j];
//            a[j] = temp;
//        }
//
//        return new String(a);
//    }
//
//    private static String fiveShuffle(SecureRandom secureRandom, String inputString) {
//
//        // Convert your string into simple char array
//        char a[] = inputString.toCharArray();
//
//        // Scramble the letters using the standard Fisher-Yates shuffle
//        for (int i = 0; i < a.length; i++) {
////        for (int i = a.length; i > 0; i--) { // Was done using reverse mechanism to improve performance
//
//            int j = secureRandom.nextInt(a.length);
//
//            // swap letters
//            char temp = a[i];
//            a[i] = a[j];
//            a[j] = temp;
//        }
//
//        return new String(a);
//    }
//
//    private static int calculateFactorial(int length) {
//        if (length <= 1) {
//            return 1;
//        } else {
//            return length * calculateFactorial(length - 1);
//        }
//    }
//
//    for (int i = 0; i < calculateFactorial(scrambled_word_length); i++) {
//        String solved_word = shuffle(random, scrambled_word);
//        String three_solved_word = threeShuffle(random, scrambled_word);
//        if (dictionaryMap.containsKey(solved_word.toUpperCase())) {
//            result.append(solved_word).append("\n");
//        }
//        if (dictionaryMap.containsKey(three_solved_word.toUpperCase())) {
//            result.append(three_solved_word).append("\n");
//        }
//    }
//
//
//}
