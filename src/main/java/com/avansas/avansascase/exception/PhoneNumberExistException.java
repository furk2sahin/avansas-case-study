package com.avansas.avansascase.exception;

public class PhoneNumberExistException extends IllegalArgumentException{
    public PhoneNumberExistException() {
        super("Phone number already in use.");
    }
}
