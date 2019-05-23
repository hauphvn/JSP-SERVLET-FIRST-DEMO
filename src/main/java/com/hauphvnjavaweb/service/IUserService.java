package com.hauphvnjavaweb.service;

import com.hauphvnjavaweb.model.UserModel;

public interface IUserService {
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
