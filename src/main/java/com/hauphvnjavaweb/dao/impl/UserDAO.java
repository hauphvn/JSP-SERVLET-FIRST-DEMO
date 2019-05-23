package com.hauphvnjavaweb.dao.impl;

import java.util.List;

import com.hauphvnjavaweb.dao.IUserDAO;
import com.hauphvnjavaweb.mapper.RowMapper;
import com.hauphvnjavaweb.mapper.UserMapper;
import com.hauphvnjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO{

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		String sql = "SELECT * FROM user AS u INNER JOIN role AS r ON u.roleid = r.id WHERE u.username = ? AND u.password = ? AND status = ?";
		List<UserModel> listUser =  query(sql, new UserMapper(), username, password, status);
		return (listUser.isEmpty() ? null : listUser.get(0));
	}

}


