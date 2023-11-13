package dev.chanwook.springstudy.domain.post.infra;

import java.util.List;

import dev.chanwook.springstudy.domain.post.Post;

public interface PostPort {
	List<Post> getList();
}
