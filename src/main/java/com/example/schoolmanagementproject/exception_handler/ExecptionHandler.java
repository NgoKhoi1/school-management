package com.example.schoolmanagementproject.exception_handler;

import com.example.schoolmanagementproject.error_response.StudentErrorResponse;
import com.example.schoolmanagementproject.exception.BadInputException;
import com.example.schoolmanagementproject.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExecptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StudentErrorResponse> studentNotFound(NotFoundException exc){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadInputException.class)
    public ResponseEntity<StudentErrorResponse> studentBadInput(BadInputException exc){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
