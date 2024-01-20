package com.ap_express_server.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoResponse {
	private Integer id;
	private String username;
	private String email;
	private List<String> roles;

	public UserInfoResponse(Integer id, String username, String email, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
