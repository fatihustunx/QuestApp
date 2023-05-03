package com.project.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	List<Like> findByPostIdAndUserId(Long postId, Long userId);

	List<Like> findByPostId(Long postId);

	List<Like> findByUserId(Long userId);

}
