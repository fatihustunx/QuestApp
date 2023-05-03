package com.project.questapp.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {

	private Long id;
	private Long postId;
	private Long userId;
}
