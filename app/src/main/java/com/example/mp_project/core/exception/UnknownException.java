package com.example.mp_project.core.exception;

/**
 * Thrown if an unknown error occurs
 */
public class UnknownException extends ClashAPIException
{
	private static final long serialVersionUID = 1916833045937890576L;

	public UnknownException(String message) 
    {
        super(message);
    }
}
