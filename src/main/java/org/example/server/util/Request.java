package org.example.server.util;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String path;
    private String contentType;
    private String request;
    private String body;
    private Map<String, String> headers;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

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

    public void parseRequest(String header) {
        String[] headerParts = header.split("\r\n");

        String firstLine = headerParts[0];
        String[] methodPath = firstLine.split(" ");
        this.setMethod(methodPath[0]);
        this.setPath(methodPath[1]);
        this.setContentType(headerParts[4]);
    }

}
