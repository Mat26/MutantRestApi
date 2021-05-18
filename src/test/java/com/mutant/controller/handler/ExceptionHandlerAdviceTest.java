package com.mutant.controller.handler;

import com.mutant.controller.dto.ErrorMessageDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

public class ExceptionHandlerAdviceTest {
    ErrorMessageDto error,errorIllegal;
    private ExceptionHandlerAdvice exceptionHandlerAdvice;
    private IllegalArgumentException ex;
    @Before
    public void setUp() throws Exception {
        exceptionHandlerAdvice = new ExceptionHandlerAdvice();
        error = ErrorMessageDto.of(String.valueOf(HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST.getReasonPhrase(),ErrorMessageDto.GENERAL_MSG_ERROR);
        errorIllegal = ErrorMessageDto.of(String.valueOf(HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST.getReasonPhrase(),null);
        ex = new IllegalArgumentException();
    }


    @Test
    public void errorNoReadableExceptionTest(){
        exceptionHandlerAdvice.errorNoReadableException();
        Assert.assertEquals(error.getStatus(),String.valueOf(HttpStatus.BAD_REQUEST.value()));
        Assert.assertEquals(error.getError(),HttpStatus.BAD_REQUEST.getReasonPhrase());
        Assert.assertEquals(error.getMessage(), ErrorMessageDto.GENERAL_MSG_ERROR);
        Assert.assertEquals(error.toString(), "ErrorMessage{status='400',error='Bad Request', message= THE REQUEST FORMAT IS NOT VALID'}");
    }

    @Test
    public void errorIllegalArgumentExceptionTest(){
        exceptionHandlerAdvice.errorIllegalArgumentException(ex);
        Assert.assertEquals(errorIllegal.getStatus(),String.valueOf(HttpStatus.BAD_REQUEST.value()));
        Assert.assertEquals(errorIllegal.getError(),HttpStatus.BAD_REQUEST.getReasonPhrase());
        Assert.assertEquals(errorIllegal.getMessage(), ex.getMessage());
    }
}