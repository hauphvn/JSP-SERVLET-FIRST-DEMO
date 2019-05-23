package com.hauphvnjavaweb.mapper;

import java.sql.ResultSet;

import com.hauphvnjavaweb.model.RoleModel;
import com.hauphvnjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel userModel = new UserModel();
		try {
			userModel.setId(resultSet.getLong("id"));
			userModel.setUsername(resultSet.getString("username"));
			userModel.setPassword(resultSet.getString("password"));
			userModel.setFullname(resultSet.getString("fullname"));
			userModel.setStatus(resultSet.getInt("status"));
//			userModel.setRoleid(resultSet.getLong("roleid")); TODO WHY DO NOT MAP FOR roleid
			try {
				RoleModel roleModel = new RoleModel();
				roleModel.setCode(resultSet.getString("code"));
				roleModel.setName(resultSet.getString("name"));
				userModel.setRole(roleModel);
			}catch(Exception e){
				System.out.println("Loi trong UserMapper: "+ e.getMessage());
			}
			userModel.setCreateDate(resultSet.getTimestamp("createdate"));
			userModel.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			userModel.setCreatedBy(resultSet.getString("createdby"));
			userModel.setModifiedBy(resultSet.getString("modifiedby"));
			return userModel;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
