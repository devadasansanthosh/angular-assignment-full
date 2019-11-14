package com.stackroute.favouriteservice.model;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

	@Id
    private String pid;
  
	@JsonProperty("name")
    private String name;

    @JsonProperty("fullName")
    private String fullName;
    
    public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	

	public Player(String fullName, String name, String pid) {
		super();
		this.pid = pid;
		this.name = name;
		this.fullName = fullName;
	}

	public Player() {
		super();
	}

	
	@Override
	public String toString() {
		return "[ fullName=" + fullName + ", name=" + name + ", pid=" + pid + "]";
	}

	    
}
