package com.project.questapp.responses;

import com.project.questapp.entities.User;

import lombok.Data;

@Data
public class UserResponse {

	Long id;
	int avatarId;
	String userName;
	
	public UserResponse(User user) {
		this.id=user.getId();
		this.avatarId=user.getAvatar();
		this.userName=user.getUserName();
	}
}
