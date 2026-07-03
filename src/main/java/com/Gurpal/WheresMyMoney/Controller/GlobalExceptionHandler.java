package com.Gurpal.WheresMyMoney.Controller;

import com.Gurpal.WheresMyMoney.Exception.ExceptionResponse;
import com.Gurpal.WheresMyMoney.Exception.NotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<?> exceptionCatch(NotExistException e)
    {
        ExceptionResponse exceptionResponse = new ExceptionResponse(e.getMessage());
        return ResponseEntity.ok(exceptionResponse);
    }
}
