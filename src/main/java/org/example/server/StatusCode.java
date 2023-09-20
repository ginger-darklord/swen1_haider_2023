package org.example.server;

public enum StatusCode {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD REQUEST"),
    UNAUTHORIZED_ERROR(401, "UNAUTHORIZED ERROR"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD NOT ALLOWED"),
    TEAPOT(418, "IM A TEAPOT"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR");


    public int code;
    public String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
