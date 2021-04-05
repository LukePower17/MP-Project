package com.example.mp_project.exception;

/**
 * Thrown when the URL is invalid
 */
public class NotFoundException extends ClashException {

    public NotFoundException(String message) {
        super(message);
    }
}
