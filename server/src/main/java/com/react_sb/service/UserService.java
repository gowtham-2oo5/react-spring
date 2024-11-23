package com.react_sb.service;

import java.util.List;

import com.react_sb.model.UserModel;

public interface UserService {

	public List<UserModel> getAllUsers();


	public Boolean addUser(UserModel user);

	public Boolean updateUser(Integer id, UserModel user);

	public Boolean deleteUser(Integer id);

	// Forgot to add this
	UserModel getUserById(Integer id);

}
