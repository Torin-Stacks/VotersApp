package com.torin.votersapp.exceptions;

public class WrongVoterCardNumberException extends RuntimeException {
    public  WrongVoterCardNumberException(String message){
         super(message);
    }
}
