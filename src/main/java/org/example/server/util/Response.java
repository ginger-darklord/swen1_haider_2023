package org.example.server.util;

import org.example.server.StatusCode;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Response {
    private int status;
    private String message;
    private String body;
    private String contentType;
    private int messageLength;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public void setMessageLength(int messageLength) {
        this.messageLength = messageLength;
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

    public void send(OutputStreamWriter osw) throws IOException {
        osw.write("HTTP/1.1 " + this.getStatus() + " " +  this.getMessage() + "\r\n");
        osw.write("Content-Type: "+ this.getContentType() + "\r\n");
        osw.write("Content-Length: 0\r\n");
        osw.write("\r\n\r\n");
    }
}
