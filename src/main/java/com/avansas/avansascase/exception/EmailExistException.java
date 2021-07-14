package com.avansas.avansascase.exception;

public class EmailExistException extends IllegalArgumentException{
    public EmailExistException() {
        super("Email already in use.");
    }
}
