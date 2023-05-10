package com.mycrudapp.springcrud.Controller;

import com.mycrudapp.springcrud.ContactErrorResponse;
import com.mycrudapp.springcrud.ContactNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ContactRESTExceptionHandler
{
    //add exception handler
    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleException(ContactNotFoundException exc)
    {
        ContactErrorResponse error = new ContactErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTime(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ContactErrorResponse> handleException(Exception exc)
    {
        ContactErrorResponse error = new ContactErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTime(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
