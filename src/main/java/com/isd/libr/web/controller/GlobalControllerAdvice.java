package com.isd.libr.web.controller;

import com.isd.libr.service.RepeatedVoteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> handleRepeatedVoteException(RepeatedVoteException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}