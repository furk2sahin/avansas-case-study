package com.avansas.avansascase.exception;

public class InvalidBirthDateException extends IllegalArgumentException{
    public InvalidBirthDateException() {
        super("Invalid birth date.");
    }
}
