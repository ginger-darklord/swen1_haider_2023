package org.example.server.util;

import org.example.server.StatusCode;

public class Response {
    private int status;
    private String message;
    private String content;
    private String contentType;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.status = statusCode.code;
        this.message = statusCode.message;
    }

    public void printResponse() {
        System.out.println("Status-Code: " + this.getStatus());
        System.out.println("Status-Message: " + this.getMessage());
    }
}