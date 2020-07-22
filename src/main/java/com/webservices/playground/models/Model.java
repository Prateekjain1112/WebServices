package com.webservices.playground.models;

public class Model {
	private String message;
	private int n;
	
	public Model(String message, int n){
		this.message = message;
		this.n = n;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	
}
