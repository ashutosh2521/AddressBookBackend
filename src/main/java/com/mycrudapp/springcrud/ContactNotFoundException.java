package com.mycrudapp.springcrud;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ContactNotFoundException extends  RuntimeException
{
    public ContactNotFoundException(String message)
    {
        super(message);
    }
}
