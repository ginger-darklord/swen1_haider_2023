package org.example.server.util;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String path;
    private String contentType;
    private String request;
    private String body;
    private String authorization;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Request() {

    }
    public Request(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public void parseRequest(String header) {
        String[] headerParts = header.split("\r\n");

        String firstLine = headerParts[0];
        String[] methodPath = firstLine.split(" ");
        this.setMethod(methodPath[0]);
        this.setPath(methodPath[1]);
        for(String part : headerParts) {
            if(part.equals("Content-Type: application/json")) {
                this.setContentType(part);
            } else if (part.equals("Authorization: Bearer admin-mtcgToken")) {
                this.setAuthorization(part);
            }
        }
    }

}
