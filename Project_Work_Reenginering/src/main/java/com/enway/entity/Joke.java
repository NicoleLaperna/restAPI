package com.enway.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Joke {
	
	int id;
	
	String joke;
	
	@Id
	@GeneratedValue
	int id_db;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJoke() {
		return joke;
	}
	public void setJoke(String joke) {
		this.joke = joke;
	}
	public int getId_db() {
		return id_db;
	}
	public void setId_db(int id_db) {
		this.id_db = id_db;
	}
	
	public Joke() {}
	
	public Joke(int id, String joke, int id_db) {
		super();
		this.id = id;
		this.joke = joke;
		this.id_db = id_db;
	}
	
}
