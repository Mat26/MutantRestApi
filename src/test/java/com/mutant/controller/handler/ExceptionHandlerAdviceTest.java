package com.mutant.controller.handler;

import com.mutant.controller.dto.ErrorMessageDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ExceptionHandlerAdviceTest {
    ErrorMessageDto error;
    private ExceptionHandlerAdvice exceptionHandlerAdvice;
    @Before
    public void setUp() throws Exception {
        exceptionHandlerAdvice = new ExceptionHandlerAdvice();
        error = ErrorMessageDto.builder().status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ErrorMessageDto.GENERAL_MSG_ERROR).build();
    }


    @Test
    public void errorNoReadableExceptionTest() throws Exception {
        exceptionHandlerAdvice.errorNoReadableException();
        Assert.assertEquals(error.getStatus(),String.valueOf(HttpStatus.BAD_REQUEST.value()));
        Assert.assertEquals(error.getError(),HttpStatus.BAD_REQUEST.getReasonPhrase());
        Assert.assertEquals(error.getMessage(), ErrorMessageDto.GENERAL_MSG_ERROR);
        Assert.assertEquals(error.toString(), "ErrorMessage{status='400',error='Bad Request', message= THE REQUEST FORMAT IS NOT VALID'}");
    }
}