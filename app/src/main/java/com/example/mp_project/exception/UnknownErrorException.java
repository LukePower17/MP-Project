package com.example.mp_project.exception;

/**
 * Thrown if an unknown error occurs
 */
public class UnknownErrorException extends ClashException {

    public UnknownErrorException(String message) {
        super(message);
    }
}
