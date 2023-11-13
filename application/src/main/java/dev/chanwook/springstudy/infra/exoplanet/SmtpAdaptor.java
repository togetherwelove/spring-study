package dev.chanwook.springstudy.infra.exoplanet;

import org.springframework.stereotype.Component;

import dev.chanwook.springstudy.domain.auth.infra.SmtpException;
import dev.chanwook.springstudy.domain.auth.infra.SmtpPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SmtpAdaptor implements SmtpPort {

	@Override
	public void send(String email) throws SmtpException {
		throw new UnsupportedOperationException("Unimplemented method 'send'");
		// TODO 회원 확인 이메일 보내기 기능 구현
	}
}
