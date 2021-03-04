package com.example.demo.roulette.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BuildGenericResponseController<T> {

  /*  public ResponseEntity<RestResponse<Map>> buildListResponse(String value, HttpStatus status, Map obj, String errorMsg ){
        return new ResponseEntity<>(
                new RestResponse<Map>(status.value(), errorMsg, value , obj),
                HttpStatus.valueOf(status.name()));
    }*/

    public ResponseEntity<RestResponse<T>> buildResponse(String message,  T obj){
        return new ResponseEntity<>(
                new RestResponse<>(message, obj), HttpStatus.OK);
    }

  /*  public ResponseEntity<RestResponse<T>> buildResponse(String message, HttpStatus status){
        return buildResponse(message, status, null, null);
    }

    public ResponseEntity<RestResponse<T>> buildErrorResponse(String value, HttpStatus status){
        return buildResponse(null, status, null, value);
    }*/
}