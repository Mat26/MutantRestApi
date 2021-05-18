package com.mutant.controller.dto;
import lombok.Getter;


@Getter
public class ErrorMessageDto {

    public static final String GENERAL_MSG_ERROR = "THE REQUEST FORMAT IS NOT VALID";
    private String status;
    private String error;
    private String message;

    private ErrorMessageDto(String status,String error, String message){
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public static ErrorMessageDto of(String status,String error, String message){ return new ErrorMessageDto(status,error,message); }

    @Override
    public String toString(){
        return "ErrorMessage{" +
                "status='" + status + '\'' +
                ",error='" + error + '\'' +
                ", message= " + message + '\'' +
                '}';
    }
}
