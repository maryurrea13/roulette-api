package com.example.demo.roulette.model;

import java.io.Serializable;

import com.example.demo.user.model.User;

public class Bet implements Serializable{

	    private static final long serialVersionUID = 1L;
		private String value;
	    private Double cash;
	    private User user;

	    public Bet(String value, Double cash, User user) {
	        this.value = value;this.cash = cash;
	        this.user = user;
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public Double getCash() {
	        return cash;
	    }

	    public void setCash(Double cash) {
	        this.cash = cash;
	    }
	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }
}
