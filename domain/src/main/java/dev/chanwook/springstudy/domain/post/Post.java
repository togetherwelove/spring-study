package dev.chanwook.springstudy.domain.post;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Post {
	private String author;
	private String content;
	private LocalDateTime createdAt;
	private Long id;
	private String title;
	private LocalDateTime updatedAt;
}
