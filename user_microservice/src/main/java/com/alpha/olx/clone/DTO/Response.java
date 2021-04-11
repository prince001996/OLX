package com.alpha.olx.clone.DTO;

import org.springframework.http.HttpStatus;

public class Response {
    private HttpStatus status;
    private String message;

    public Response(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
