package com.jta28022018.jta.controller;


import com.jta28022018.jta.model.ErrorToSave;
import com.jta28022018.jta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorController {

    @Autowired
    UserService userService;

/*    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> getErrorResponse(DataIntegrityViolationException e){
//        e.getMessage()
//e.getClass().getName()
        //ResponseEntity<String> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        //ResponseEntity<?> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        //ResponseEntity<Map<String,List<String>> responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        //return new ResponseEntity<>("AAAAAAAAAAAAAAAAAAAAAAAAAAAeeeeerrrorrr!!!", HttpStatus.BAD_REQUEST);
    }*/



    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> getErrorResponse(RuntimeException e){
        ErrorToSave errorToSave = new ErrorToSave();
        errorToSave.setCreatedOn(new Date());
        //errorToSave.setMessage(e.getMessage());
        errorToSave.setMessage(e.getCause().getLocalizedMessage());
        errorToSave.setExceptionType(e.getClass().getTypeName());
        userService.save(errorToSave);
        //return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e.getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<?> getErrorResponse(NoSuchElementException e){
        ErrorToSave errorToSave = new ErrorToSave();
        errorToSave.setCreatedOn(new Date());
        //errorToSave.setMessage(e.getMessage());
        errorToSave.setMessage(e.getCause().getLocalizedMessage());
        errorToSave.setExceptionType(e.getClass().getTypeName());
        userService.save(errorToSave);
        //return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(e.getCause().getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
