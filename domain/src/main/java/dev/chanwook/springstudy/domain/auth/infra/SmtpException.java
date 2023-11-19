package dev.chanwook.springstudy.domain.auth.infra;

public class SmtpException extends RuntimeException{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

    public SmtpException(String message) {
        super(message);
    }

    public SmtpException(String message, Throwable exception) {
        super(message, exception);
    }

    public SmtpException(Throwable exception) {
        super(exception);
    }

}
