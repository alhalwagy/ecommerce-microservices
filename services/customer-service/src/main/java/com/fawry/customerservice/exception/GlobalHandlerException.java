package com.fawry.customerservice.exception;


import com.fawry.customerservice.exception.customExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorModel> resourceNotFound(ResourceNotFoundException e) {
    ErrorModel errorModel =
        ErrorModel.builder()
            .message(e.getMessage())
            .code(String.valueOf(HttpStatus.NOT_FOUND))
            .build();

    return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
  }
}
