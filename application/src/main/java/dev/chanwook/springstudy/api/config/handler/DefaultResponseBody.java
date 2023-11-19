package dev.chanwook.springstudy.api.config.handler;

import dev.chanwook.springstudy.api.config.common.ResponseCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponseBody {
	public static class DefaultResponseBodyBuilder {}
	private ResponseCodeEnum code;
	private Object data;
	private String description;

	private String message;
}
