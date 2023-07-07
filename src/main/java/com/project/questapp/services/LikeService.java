package com.project.questapp.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.LikeRepository;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.responses.LikeResponse;

@Service
public class LikeService {

	private LikeRepository likeRepository;
	private UserService userService;
	private PostService postService;

	public LikeService(LikeRepository likeRepository, UserService userService, @Lazy PostService postService) {
		this.likeRepository = likeRepository;
		this.userService = userService;
		this.postService=postService;
	}

	public List<LikeResponse> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
		List<Like> list;
		if (postId.isPresent() && userId.isPresent()) {
			list= likeRepository.findByPostIdAndUserId(postId.get(), userId.get());
		} else if (postId.isPresent()) {
			list= likeRepository.findByPostId(postId.get());
		} else if (userId.isPresent()) {
			list= likeRepository.findByUserId(userId.get());
		} else {
			list= likeRepository.findAll();
		}
		
		return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
	}

	public Like createOneLike(LikeCreateRequest newLikeCreateRequest) {
		User user = userService.getOneUserById(newLikeCreateRequest.getUserId());
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