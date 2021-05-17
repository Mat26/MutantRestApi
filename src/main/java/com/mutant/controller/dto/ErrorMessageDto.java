package com.mutant.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorMessageDto {

    public static final String GENERAL_MSG_ERROR = "THE REQUEST FORMAT IS NOT VALID";
    private String status;
    private String error;
    private String message;

    @Override
    public String toString(){
        return "ErrorMessage{" +
                "status='" + status + '\'' +
                ",error='" + error + '\'' +
                ", message= " + message + '\'' +
                '}';
    }
}
