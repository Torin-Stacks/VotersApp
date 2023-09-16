package com.torin.votersapp.exceptions;

import lombok.Data;

@Data

public class WrongBirthDateException extends RuntimeException{
    public WrongBirthDateException(String message){
        super(message);
    }
}
