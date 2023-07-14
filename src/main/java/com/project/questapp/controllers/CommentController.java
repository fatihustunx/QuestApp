package com.project.questapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.responses.CommentResponse;
import com.project.questapp.services.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}

	@GetMapping
	public List<CommentResponse> getAllComments(@RequestParam Optional<Long> postId, @RequestParam Optional<Long> userId) {
		return commentService.getAllComments(postId, userId);
	}

	@PostMapping
	public Comment createOneComment(@RequestBody CommentCreateRequest newCommentCreateRequest) {
		return commentService.createOneComment(newCommentCreateRequest);
	}

	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}

	@PutMapping("/{commentId}")
	public Comment updateOneComment(@PathVariable Long commentId,
			@RequestBody CommentUpdateRequest newCommentUpdateRequest) {
		return commentService.updateOneCommentById(commentId, newCommentUpdateRequest);
	}

	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable Long commentId) {
		this.commentService.deleteOneCommentById(commentId);
	}
}
