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
	private ResponseCodeEnum code;
	private String message;
	private Object data;
	private String description;
	
	public static class DefaultResponseBodyBuilder {}
}
