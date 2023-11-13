package dev.chanwook.springstudy.infra.post;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.chanwook.springstudy.domain.post.Post;
import dev.chanwook.springstudy.domain.post.infra.PostPort;
import dev.chanwook.springstudy.infra.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostAdaptor implements PostPort {

	private final PostRepository postRepository;

	@Override
	public List<Post> getList() {
		return postRepository.findAll().stream().map(p -> Post.builder()
				.id(p.getId())
				.author(p.getAuthor())
				.title(p.getTitle())
				.content(p.getContent())
				.build()).collect(Collectors.toList());
	}

}
