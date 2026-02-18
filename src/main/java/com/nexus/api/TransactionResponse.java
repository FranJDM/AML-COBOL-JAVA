package com.nexus.api;

public class TransactionResponse {
    private String status;
    private String message;

    public TransactionResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters necesarios para que frameworks como Jackson o Spring 
    // puedan convertir este objeto a JSON automáticamente
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransactionResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}