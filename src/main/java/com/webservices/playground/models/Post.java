package com.webservices.playground.models;

public class Post {
	
	private Integer id;
	private String body;
	private int likes;
	private int dislikes;
	
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDislikes() {
		return dislikes;
	}
	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", body=" + body + ", likes=" + likes + ", dislikes=" + dislikes + "]";
	}
}
