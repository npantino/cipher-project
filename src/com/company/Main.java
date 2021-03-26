package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        /*
        Caesar Cipher
        (a = 1, b = 2, ... y = 25, z = 26)
        encrypt hello world , shift = 1
        => 8 5 13 13 15 -1 23 15 18 13 4
        + 1 => 9 6 14 14 16 -1 24 16 14 5
        => ifmmp xpsme
        */

        ArrayList<Integer> list = new ArrayList<>();

        StringBuilder encryptedMsg = new StringBuilder();

        Scanner scanner = new Scanner(System.in);

        String originalMsg = "";
        String choice = "";
        String errMsg = "Error, please try again";
        boolean correctChoice = true;

        // Some user input, and control flow with an error message

        while (correctChoice) {
            System.out.println("Encrypt or Decrypt? ");
            choice = scanner.nextLine().toLowerCase();
            if (choice.equals("encrypt")) {
                System.out.println("Enter message to be encrypted: ");
                originalMsg = scanner.nextLine();
                correctChoice = false;
            }
            else if (choice.equals("decrypt")) {
                System.out.println("Enter message to be decrypted: ");
                originalMsg = scanner.nextLine();
                correctChoice = false;
            }
            else {
                System.out.println(errMsg);
            }
        }

        // Shift number refers to amount of times a letter will be shifted relative to the alphabet.
        System.out.println("Enter shift number (0 - 26): ");
        int shift = scanner.nextInt();

        // Decrypt means shifting letters in reverse (I => H)
        if (choice.equals("decrypt")) {
            shift = shift * -1;
        }

        // Where the actual encryption/decryption happens, loops each letter in the original message
        for(int i = 0; i < originalMsg.length(); i++) {

            if (Character.getNumericValue(originalMsg.charAt(i)) >= 0) {
                // Converting letters to numbers (A = 1, B = 2... Z = 26... space = -1)
                list.add(Character.getNumericValue(originalMsg.charAt(i)) - 10);
                // Using modulus so integers will always be between 0-25
                // Adding 97 so integers can be converted back to letters using base 10 to ASCII characters
                list.set(i, ((list.get(i) + shift + 26) % 26) + 97);
            }

            // Spaces are set to 32
            else {
                list.add(32);
            }
            int l = list.get(i);
            // Base 10 => ASCII characters (a, b, c...)
            String s = Character.toString((char)l);
            // adding letters to encrypted message using StringBuilder
            encryptedMsg.append(s);
        }

        // Encrypted/decrypted message
        System.out.println(encryptedMsg);

    }
}
