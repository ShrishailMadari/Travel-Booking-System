package com.shyloostyle.userservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String message;
    private String errorCode;
    private String timestamp;

    public ErrorMessage(String message, String errorCode, String timestamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }


}
