package com.torin.votersapp.exceptions;

public class CandidateNotFoundException extends RuntimeException{
   public CandidateNotFoundException(String message){
        super(message);
    }
}
