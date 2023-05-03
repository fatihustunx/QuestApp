package com.project.questapp.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {

	private Long id;
	String text;
	private Long postId;
	private Long userId;
}
