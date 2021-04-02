package com.company;

import java.util.ArrayList;
import java.util.Random;

public class OneTimePad implements Cipher {
    ArrayList<Integer> encryptedList = new ArrayList<>();
    ArrayList<Integer> pad = new ArrayList<>();
    StringBuilder encryptedMsg = new StringBuilder();
    StringBuilder padString = new StringBuilder();
    Random random = new Random();

    // converts original message from characters to integers
    public void convertMsg(String str) {

        for(int i = 0; i < str.length(); i++) {
            // Converting letters to numbers (A = 0, B = 1 ... Z = 25 ... space = -11)
            encryptedList.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // Generates pad with random integers between 0 and 25, length is equal to original message
    public void generatePad(String str) {
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
    public void convertPadInt(String str) {
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

    public void convertPadString(String str) {
        for(int i = 0; i < str.length(); i++) {
            pad.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // multiplies each integer in pad by -1 by looping through the ArrayList for pad
    public void reversePad(String str) {
        for(int i = 0; i < str.length(); i++) {
            pad.set(i, pad.get(i) * -1);
        }
    }

    // Encrypted/Decrypts original message by using shifts depending on the cipher, and adds them to another ArrayList.
    public void shift(String str) {
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

    // Converts encrypted/decrypted ArrayList from integers to characters, and adds them to encrypted/decrypted message
    public void convertList(String str) {
        for(int i = 0; i < str.length(); i++) {
            int l = encryptedList.get(i);
            // Base 10 => ASCII characters (a, b, c...)
            String s = Character.toString((char)l);
            // adding letters to encrypted message using StringBuilder
            encryptedMsg.append(s);
        }
        System.out.println(encryptedMsg);
    }

    @Override
    public void shift() {

    }
}