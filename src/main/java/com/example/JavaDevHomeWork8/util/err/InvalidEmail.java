package com.example.JavaDevHomeWork8.util.err;

public class InvalidEmail extends Exception{

    public InvalidEmail(String message) {
        super(message);
    }

    public InvalidEmail() {
        super("Your email address is invalid. Check it all.");
    }
}
