package com.react_sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // TO create an appropriate table in your database
@Table(name = "users") // TO give a custom name to your table
public class UserModel {

	@Id // TO specify that this field will be the PK of your table (Primary Key)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // For auto generation of ID
	private Integer id;
	private String name;
	private String email;
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
