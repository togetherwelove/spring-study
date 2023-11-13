package dev.chanwook.springstudy.domain.post.api;

import java.util.List;

import dev.chanwook.springstudy.domain.post.Post;

public interface PostUsecase {

	public List<Post> getList();
}
