package com.shyloostyle.userservice.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String string) {
        super(string);
    }
}
