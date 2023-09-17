package com.torin.votersapp.exceptions;

public class WrongPasswordException extends RuntimeException{
   public WrongPasswordException(String message){
       super(message);
   }
}
