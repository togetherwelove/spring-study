package dev.chanwook.springstudy.api.config.common;

public enum ResponseCodeEnum {
	ERROR("fail"),
	SUCCESS("success");

	private final String description;

	ResponseCodeEnum(final String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
}
