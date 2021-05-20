package com.mutant.controller.handler;

import com.mutant.controller.dto.ErrorMessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    ErrorMessageDto errorMsg;

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageDto> errorNoReadableException() {
        errorMsg = ErrorMessageDto.of(String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase(), ErrorMessageDto.GENERAL_MSG_ERROR);
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorMessageDto> errorIllegalArgumentException(IllegalArgumentException ex) {
        errorMsg = ErrorMessageDto.of(String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

}
