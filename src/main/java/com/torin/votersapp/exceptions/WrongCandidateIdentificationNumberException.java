package com.torin.votersapp.exceptions;

public class WrongCandidateIdentificationNumberException extends RuntimeException{
    public WrongCandidateIdentificationNumberException(String message){
        super(message);
    }
}
