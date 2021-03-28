package com.company;

import java.util.ArrayList;

public class CaesarCipher implements Cipher {

    public static ArrayList<Integer> encryptedList = new ArrayList<>();
    public static StringBuilder encryptedMsg = new StringBuilder();

    // converts original message from characters to integers
    public static void convertMsg(String str) {

        for(int i = 0; i < str.length(); i++) {
            // Converting letters to numbers (A = 0, B = 1 ... Z = 25 ... space = -11)
            encryptedList.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    public static void shift(int shift, String str) {
        for(int i = 0; i < str.length(); i++) {

            if (Character.getNumericValue(str.charAt(i)) >= 0) {
                // Using modulus so integers will always be between 0-25
                // Adding 97 so integers can be converted back to letters using base 10 to ASCII characters
                encryptedList.set(i, ((encryptedList.get(i) + shift + 26) % 26) + 97);
            }
            // Spaces are set to 32
            else {
                encryptedList.set(i, 32);
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
