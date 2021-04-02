package com.company;

import java.util.ArrayList;

public class CaesarCipher implements Cipher {

    ArrayList<Integer> encryptedList = new ArrayList<>();
    StringBuilder encryptedMsg = new StringBuilder();

    // Converts original message from characters to integers
    public void convertMsg(String str) {

        for (int i = 0; i < str.length(); i++) {
            // Converting letters to numbers (A = 0, B = 1 ... Z = 25 ... space = -11)
            encryptedList.add(Character.getNumericValue(str.charAt(i)) - 10);
        }
    }

    // Encrypted/Decrypts original message by using shifts depending on the cipher, and adds them to another ArrayList.
    public void shift(int shift, String str) {
        for (int i = 0; i < str.length(); i++) {

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

    public void convertList(String str) {
        for (int i = 0; i < str.length(); i++) {
            int l = encryptedList.get(i);
            // Base 10 => ASCII characters (a, b, c...)
            String s = Character.toString((char) l);
            // adding letters to encrypted/decrypted message using StringBuilder
            encryptedMsg.append(s);
        }
        System.out.println(encryptedMsg);
    }

    @Override
    public void shift() {

    }

}
