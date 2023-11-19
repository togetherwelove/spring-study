package dev.chanwook.springstudy.infra.post.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import dev.chanwook.springstudy.infra.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posts extends BaseEntity {

	private String author;

	private String content;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id private Long id;

	private String title;

}
