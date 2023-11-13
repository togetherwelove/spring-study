package dev.chanwook.springstudy.domain.auth.infra;

public interface SmtpPort {
	void send(String email) throws SmtpException;
}
