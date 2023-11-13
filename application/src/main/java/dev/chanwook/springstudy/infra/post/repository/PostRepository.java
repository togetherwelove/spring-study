package dev.chanwook.springstudy.infra.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.chanwook.springstudy.infra.post.repository.entity.Posts;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long>{

}
