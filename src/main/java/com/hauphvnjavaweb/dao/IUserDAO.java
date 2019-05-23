package com.hauphvnjavaweb.dao;

import com.hauphvnjavaweb.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
