package com.torin.votersapp.exceptions;

public class IllegibleCandidateException extends RuntimeException{
    public IllegibleCandidateException(String message){
        super(message);
    }
}