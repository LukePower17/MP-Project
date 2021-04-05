package com.example.mp_project.exception;

/**
 * Thrown when rate limit has been exceeded.
 */
public class RateLimitExceededException extends ClashException {

    public RateLimitExceededException(String message) {
        super(message);
    }
}
