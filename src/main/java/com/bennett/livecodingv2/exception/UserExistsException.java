package com.bennett.livecodingv2.exception;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String message){
        super(message);
    }
}
