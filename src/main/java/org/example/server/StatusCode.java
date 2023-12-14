package org.example.server;

public enum StatusCode {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED_ERROR(401, "UNAUTHORIZED_ERROR"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT_FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED"),
    TEAPOT(418, "IM_A_TEAPOT"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER ERROR");


    public int code;
    public String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
