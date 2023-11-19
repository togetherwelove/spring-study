package dev.chanwook.springstudy.infra.exoplanet;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmtpAdaptorTest {

	@Autowired
	SmtpAdaptor smtpAdaptor;

	@Test
	@DisplayName("이메일 전송 기능 테스트")
	void send() {
		assertThrowsExactly(UnsupportedOperationException.class,
				() -> smtpAdaptor.send("user@user.dev"), "");
	}
}
