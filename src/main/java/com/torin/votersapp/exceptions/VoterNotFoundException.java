package com.torin.votersapp.exceptions;

public class VoterNotFoundException extends RuntimeException {
    public VoterNotFoundException(String message) {
        super(message);
    }
}
