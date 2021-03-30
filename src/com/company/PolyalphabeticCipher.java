package com.company;

import java.util.ArrayList;

public class PolyalphabeticCipher implements Cipher {

    public static ArrayList<Integer> shiftList = new ArrayList<>();
    public static ArrayList<Integer> encryptedList = new ArrayList<>();
    public static StringBuilder encryptedMsg = new StringBuilder();

    // converting individual characters in keyword to integers
    // A = 0, B = 1, C = 2 ... Y = 25, Z = 26
    public static void convertShift(String str) {
        for(int i = 0; i < str.length(); i++) {
            shiftList.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // converts original message from characters to integers
    public static void convertMsg(String str) {

        for(int i = 0; i < str.length(); i++) {
            // Converting letters to numbers (A = 0, B = 1 ... Z = 25 ... space = -11)
            encryptedList.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // multiplies each integer in shiftList by -1 by looping through ArrayList
    public static void reverseShift(String str) {
        for(int i = 0; i < str.length(); i++) {
            shiftList.set(i, shiftList.get(i) * -1);
        }
    }

    // Encrypted/Decrypts original message by using shifts depending on the cipher, and adds them to another ArrayList.
    public static void shift(String str1, String str2) {
        // increment keeps track of number of spaces
        int increment = 0;
        int len = str1.length();
        for(int i = 0; i < str2.length(); i++) {
            // - increment so the cipher doesn't skip over a char in the keyword
            // modulus to keep index in range, + len to prevent negatives
            int s = shiftList.get(((i - (increment % len)) + len) % len);
            if (Character.getNumericValue(str2.charAt(i)) >= 0) {
                // Using modulus so integers will always be between 0-25, and to cycle through shiftList
                // Adding 97 so integers can be converted back to letters using base 10 to ASCII characters
                encryptedList.set(i, ((encryptedList.get(i) + s + 26) % 26) + 97);
            }

            // Spaces are set to 32, increment increases by 1
            else {
                encryptedList.set(i, 32);
                increment++;
            }
        }
    }

    // Converts encrypted/decrypted ArrayList from integers to characters, and adds them to encrypted/decrypted message
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
