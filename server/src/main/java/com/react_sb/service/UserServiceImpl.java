package com.react_sb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.react_sb.model.UserModel;
import com.react_sb.repository.UserModelRepo;

@Service // To Specify that this class will act as a service class where the whole
			// logical operations will be performed
// This class must implement the `UserService` interface for the methods template
public class UserServiceImpl implements UserService {

	// Get the instance of UserModelRepo to perform DB operations
	@Autowired // To give userRepo a proper referring thing (Avoid null related errors)
	private UserModelRepo userRepo;

	@Override
	public List<UserModel> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public Boolean addUser(UserModel user) {
		try {
			userRepo.save(user);
			System.out.println("USer Added successfully");
			return true;
		} catch (Exception e) {
			System.err.println("Error adding user, " + e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public Boolean updateUser(Integer id, UserModel user) {
		try {
			UserModel currUser = userRepo.getUserById(id);
			// As the getById is deprecated, lets create a custom query in repository
			currUser.setPassword(user.getPassword()); // We are just modifying the password
			userRepo.save(currUser);
			return true;
		} catch (Exception e) {
			System.err.println("Error updating User, " + e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public Boolean deleteUser(Integer id) {
		try {
			UserModel currUser = userRepo.getUserById(id);
			userRepo.delete(currUser);
			return true;
		} catch (Exception e) {
			System.err.println("Error updating User, " + e.getLocalizedMessage());
			return false;
		}
	}

	@Override
	public UserModel getUserById(Integer id) {
		return userRepo.getUserById(id);
	}

}
