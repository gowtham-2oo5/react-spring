package com.react_sb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.react_sb.model.UserModel;

public interface UserModelRepo extends JpaRepository<UserModel, Integer> {

	@Query("SELECT u FROM UserModel u WHERE u.id=?1")
	public UserModel getUserById(Integer id);

}
