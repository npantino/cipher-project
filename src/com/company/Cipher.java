package com.company;

public interface Cipher {
    // Converts original message from characters to integers
    void convertMsg();

    // Encrypted/Decrypts original message by using shifts depending on the cipher, and adds them to another ArrayList.
    void shift();

    // Converts encrypted/decrypted ArrayList from integers to characters, and adds them to encrypted/decrypted message
    void convertList();
}