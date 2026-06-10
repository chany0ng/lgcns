package com.lgcns.pipeline;

public class JwtException extends RuntimeException {
    public JwtException(String msg) {
        super("JwtException: " + msg);
    }
}
