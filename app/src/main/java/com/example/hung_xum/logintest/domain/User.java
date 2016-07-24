package com.example.hung_xum.logintest.domain;

public class User {

	private Integer id;
	private String name;
	private String password;
	private String imageName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public User(){

	}
	public User(String name , String password,String imageName){
		this.name = name;
		this.password = password;
		this.imageName = imageName;
	}
}
