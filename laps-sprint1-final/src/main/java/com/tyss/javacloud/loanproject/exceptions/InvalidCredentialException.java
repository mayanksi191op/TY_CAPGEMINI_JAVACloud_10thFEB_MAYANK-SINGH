package com.tyss.javacloud.loanproject.exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialException extends RuntimeException {
	public InvalidCredentialException(String msg) {
		super(msg);
	}
}
