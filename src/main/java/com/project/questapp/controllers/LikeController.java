package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Like;
import com.project.questapp.requests.LikeCreateRequest;
import com.project.questapp.responses.LikeResponse;
import com.project.questapp.services.LikeService;

@RestController
@RequestMapping("/likes")
public class LikeController {

	private LikeService likeService;

	public LikeController(LikeService likeService) {
		this.likeService = likeService;
	}

	@GetMapping
	public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
		return likeService.getAllLikes(postId, userId);
	}

	@PostMapping
	public Like createOneLike(@RequestBody LikeCreateRequest newLikeCreateRequest) {
		return likeService.createOneLike(newLikeCreateRequest);
	}

	@GetMapping("/{likeId}")
	public Like getOneLike(@PathVariable Long likeId) {
		return likeService.getOneLikeById(likeId);
	}

	/*
	 * @PutMapping("/{likeId}") public Like updateOneLike(@PathVariable Long likeId,
	 * 
	 * @RequestBody LikeUpdateRequest newLikeUpdateRequest) { return
	 * likeService.updateLikeById(likeId, newLikeUpdateRequest); }
	 */

	@DeleteMapping("/{likeId}")
	public void deleteOneLike(@PathVariable Long likeId) {
		this.likeService.deleteOneLikeById(likeId);
	}

}
