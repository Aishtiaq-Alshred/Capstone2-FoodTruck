package com.example.cap2foodtruck.ApiResponse;

public class ApiException extends RuntimeException{

    public ApiException(String message){
        super(message);
    }
}