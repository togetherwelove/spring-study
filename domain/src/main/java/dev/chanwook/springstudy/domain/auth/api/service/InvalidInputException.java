package dev.chanwook.springstudy.domain.auth.api.service;

public class InvalidInputException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(String message, Throwable exception) {
		super(message, exception);
	}

	public InvalidInputException(Throwable exception) {
		super(exception);
	}

}
