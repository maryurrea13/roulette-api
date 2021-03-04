package com.example.demo.roulette.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BuildGenericResponseController<T> {

    public ResponseEntity<RestResponse<T>> buildResponse(String message,  T obj){
        return new ResponseEntity<>(
                new RestResponse<>(message, obj), HttpStatus.OK);
    }


}