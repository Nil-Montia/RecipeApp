package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	
	private String name;
	private String description;
	private String owner;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Recipe(String name, String description, String owner) {
		super();
		this.name = name;
		this.description = description;
		this.owner = owner;
	}
	public Recipe() {
		
	}
	
	
	
	
	
}
