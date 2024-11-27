package com.saparov.ClientContactsManager.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    public String errorCode;

    public ApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
