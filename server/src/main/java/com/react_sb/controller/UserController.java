package com.react_sb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.react_sb.model.UserModel;
import com.react_sb.service.UserService;

@RestController // TO specify that this class will be used to define REST API Endpoints
@RequestMapping("/api/users") // To specify the prefix of the endpoints which will be defined in this class
public class UserController {

	@Autowired // Same purpose as mentioned in ServiceImpl class
	private UserService userService; // Instance of the UserService interface!

	@GetMapping
	public List<UserModel> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("getById")
	public ResponseEntity<?> getUserById(@RequestParam Integer id) {
		try {
			UserModel user = userService.getUserById(id);
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error getting user by id, " + e.getLocalizedMessage());
		}
	}

	// Why ResponseEntity? For proper and clean response while testing (With
	// postman) and is considered as a best practice

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserModel entity) {
		try {
			Boolean res = userService.addUser(entity);
			return (res) ? ResponseEntity.ok().body("User Created successfully")
					: ResponseEntity.badRequest().body("Couldnt create user");
			// Used ternary operator for single line handling
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error Creating new user, " + e.getLocalizedMessage());
		}
	}

	@PutMapping("update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserModel entity) {
		try {
			Boolean res = userService.updateUser(id, entity);
			return (res) ? ResponseEntity.ok().body("User updated successfully")
					: ResponseEntity.badRequest().body("Couldnt update user");
			// Used ternary operator for single line handling
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error updating user, " + e.getLocalizedMessage());
		}
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
		try {
			Boolean res = userService.deleteUser(id);
			return (res) ? ResponseEntity.ok().body("User deleted successfully")
					: ResponseEntity.badRequest().body("Couldnt delete user");
			// Used ternary operator for single line handling
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Error deleting user, " + e.getLocalizedMessage());
		}
	}

}
