package com.torin.votersapp.exceptions;

import lombok.Data;
import lombok.Getter;

@Data
public class WrongBirthMonthException extends RuntimeException{
  public WrongBirthMonthException(String message){
        super(message);


    }
}
