package com.shyloostyle.userservice.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    @JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("error_code")
    private String errorCode;
    @JsonProperty("timestamp")
    private String timestamp;

    public ErrorMessage(int status, String message, String errorCode, String timestamp) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }


}
