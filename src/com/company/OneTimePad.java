package com.company;

import java.util.ArrayList;
import java.util.Random;

public class OneTimePad {
    public static ArrayList<Integer> encryptedList = new ArrayList<>();
    public static ArrayList<Integer> pad = new ArrayList<>();
    public static StringBuilder encryptedMsg = new StringBuilder();
    public static StringBuilder padString = new StringBuilder();
    public static Random random = new Random();

    // converts original message from characters to integers
    public static void convertMsg(String str) {

        for(int i = 0; i < str.length(); i++) {
            // Converting letters to numbers (A = 0, B = 1 ... Z = 25 ... space = -11)
            encryptedList.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // Generates pad with random integers between 0 and 25, length is equal to original message
    public static void generatePad(String str) {
        // max is exclusive when generating random integer
        int max = 26;
        for (int i = 0; i < str.length(); i++) {
            // Doesn't generate numbers for spaces
            if (Character.getNumericValue(str.charAt(i)) >= 0) {
                int randomNum = random.nextInt(max);
                pad.add(randomNum);
            }
        }
    }

    // converts pad from integers to string, and prints it
    public static void convertPadInt(String str) {
        // increment keeps track of number of spaces
        int increment = 0;
        for (int i = 0; i < str.length(); i++) {
            int j = i - increment;
            if (Character.getNumericValue(str.charAt(i)) >= 0) {
                int l = pad.get(j) + 97;
                // Base 10 => ASCII characters (a, b, c...)
                String s = Character.toString((char)l);
                // adding letters to encrypted message using StringBuilder
                padString.append(s);
            }
            else {
                increment++;
            }
        }
        System.out.println("Pad: " + padString);
    }

    public static void convertPadString(String str) {
        for(int i = 0; i < str.length(); i++) {
            pad.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // multiplies each integer in pad by -1
    public static void reversePad(String str) {
        for(int i = 0; i < str.length(); i++) {
            pad.set(i, pad.get(i) * -1);
        }
    }

    public static void shift(String str) {
        int increment = 0;
        for(int i = 0; i < str.length(); i++) {
            // Skips shifting spaces
            if (Character.getNumericValue(str.charAt(i)) >= 0) {
                // Gets shift from pad
                int j = i - increment;
                int shift = pad.get(j);
                // Using modulus so integers will always be between 0-25
                // Adding 97 so integers can be converted back to letters using base 10 to ASCII characters
                encryptedList.set(i, ((encryptedList.get(i) + shift + 26) % 26) + 97);
            }

            // Spaces are set to 32
            else {
                encryptedList.set(i, 32);
                increment++;
            }
        }
    }

    public static void convertList(String str) {
        for(int i = 0; i < str.length(); i++) {
            int l = encryptedList.get(i);
            // Base 10 => ASCII characters (a, b, c...)
            String s = Character.toString((char)l);
            // adding letters to encrypted message using StringBuilder
            encryptedMsg.append(s);
        }
        System.out.println(encryptedMsg);
    }
}