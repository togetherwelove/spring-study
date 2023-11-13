package dev.chanwook.springstudy.domain.post.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.chanwook.springstudy.domain.post.Post;
import dev.chanwook.springstudy.domain.post.api.PostUsecase;
import dev.chanwook.springstudy.domain.post.infra.PostPort;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService implements PostUsecase {

	private final PostPort postPort;

	@Override
	public List<Post> getList() {
		return postPort.getList();
	}

}
