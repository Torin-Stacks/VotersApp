package com.torin.votersapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(WrongBirthDateException.class)
    public ResponseEntity<?> handleWrongBirthDateException(WrongBirthDateException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongBirthMonthException.class)
    public ResponseEntity<?> handleWrongBirthMonthException(WrongBirthMonthException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VoterNotFoundException.class)
    public ResponseEntity<?> handleVoterNotFound(VoterNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<?> handlePasswordNotFound(WrongPasswordException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongVoterCardNumberException.class)
    public ResponseEntity<?> handleWrongVoterCardNumberException(WrongVoterCardNumberException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<?> handleCandidateNotFoundException(CandidateNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WrongCandidateIdentificationNumberException.class)
    public ResponseEntity<?> handleWrongCandidateIdentificationNumberException(WrongCandidateIdentificationNumberException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

