package com.shyloostyle.userservice.dto;

public class ApiResponseDTO {
    private String message;
    private int status;

    public ApiResponseDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "ApiResponseDTO{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
    public ApiResponseDTO() {
    }
    public ApiResponseDTO(String message) {
        this.message = message;
    }
    public ApiResponseDTO(int status) {
        this.status = status;
    }

}
