package com.oma.remittancepayoutapi.remittancepayoutapi.exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String message) {
        super(message);
    }

}