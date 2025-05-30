package com.ams.dev.api.parking.exception;


import com.ams.dev.api.parking.dto.ApiResponseErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseErrorDto> handleException(Exception e){
        ApiResponseErrorDto response = new ApiResponseErrorDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Internal Server Error");
        response.setDebugMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseErrorDto> notFoundException(Exception e){
        ApiResponseErrorDto response = new ApiResponseErrorDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Not Found Error");
        response.setDebugMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseErrorDto> badRequestException(Exception e){
        ApiResponseErrorDto response = new ApiResponseErrorDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("Not Found Error");
        response.setDebugMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
