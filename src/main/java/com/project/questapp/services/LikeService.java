package com.project.questapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.LikeRepository;
import com.project.questapp.requests.LikeCreateRequest;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;

	public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Like> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
		if (postId.isPresent() && userId.isPresent()) {
			return likeRepository.findByPostIdAndUserId(postId.get(), userId.get());
		} else if (postId.isPresent()) {
			return likeRepository.findByPostId(postId.get());
		} else if (userId.isPresent()) {
			return likeRepository.findByUserId(userId.get());
		} else {
			return likeRepository.findAll();
		}
	}

	public Like createOneLike(LikeCreateRequest newLikeCreateRequest) {
		User user = userService.getOneUser(newLikeCreateRequest.getUserId());
		Post post = postService.getOnePostById(newLikeCreateRequest.getPostId());
		if (user == null & post == null) {
			return null;
		}
		Like toSave = new Like();
		toSave.setId(newLikeCreateRequest.getId());
		toSave.setPost(post);
		toSave.setUser(user);
		return likeRepository.save(toSave);
	}

	public Like getOneLikeById(Long likeId) {
		return likeRepository.findById(likeId).orElse(null);
	}

	/*
	 * public Like updateOneLikeById(Long likeId, LikeUpdateRequest
	 * newLikeUpdateRequest) { Optional<Like> like =
	 * likeRepository.findById(likeId); if (like.isPresent()) { Like toUpdate =
	 * like.get(); // set things !! return likeRepository.save(toUpdate); } return
	 * null; }
	 */

	public void deleteOneLikeById(Long likeId) {
		this.likeRepository.deleteById(likeId);
	}
}