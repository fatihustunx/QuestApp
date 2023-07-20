package com.project.questapp.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.responses.CommentResponse;

@Service
public class CommentService {

	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<CommentResponse> getAllComments(Optional<Long> postId, Optional<Long> userId) {
		List<Comment> list;
		if (postId.isPresent() && userId.isPresent()) {
			list = commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
		} else if (postId.isPresent()) {
			list = commentRepository.findByPostId(postId.get());
		} else if (userId.isPresent()) {
			list = commentRepository.findByUserId(userId.get());
		} else {
			list = commentRepository.findAll();
		}

		return list.stream().map(comment -> new CommentResponse(comment)).collect(Collectors.toList());
	}

	public Comment createOneComment(CommentCreateRequest newCommentCreateRequest) {
		User user = userService.getOneUserById(newCommentCreateRequest.getUserId());
		Post post = postService.getOnePostById(newCommentCreateRequest.getPostId());
		if (user == null & post == null) {
			return null;
		}
		Comment toSave = new Comment();
		toSave.setId(newCommentCreateRequest.getId());
		toSave.setText(newCommentCreateRequest.getText());
		toSave.setPost(post);
		toSave.setUser(user);
		toSave.setCreateDate(new Date());
		return commentRepository.save(toSave);
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment updateOneCommentById(Long commentId, CommentUpdateRequest newCommentUpdateRequest) {
		Optional<Comment> comment = commentRepository.findById(commentId);
		if (comment.isPresent()) {
			Comment toUpdate = comment.get();
			toUpdate.setText(newCommentUpdateRequest.getText());
			return commentRepository.save(toUpdate);
		}
		return null;
	}

	public void deleteOneCommentById(Long commentId) {
		this.commentRepository.deleteById(commentId);
	}

}
