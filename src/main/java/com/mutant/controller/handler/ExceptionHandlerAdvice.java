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
    public ResponseEntity<ErrorMessageDto> errorNoReadableException(){
        errorMsg = ErrorMessageDto.builder().status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                                            .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                            .message(ErrorMessageDto.GENERAL_MSG_ERROR).build();
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }
}
