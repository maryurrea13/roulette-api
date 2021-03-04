package com.example.demo.user.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
    private String id;
    private String name = "";
    private double balance = 0.0;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
    
    
}
