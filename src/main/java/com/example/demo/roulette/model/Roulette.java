package com.example.demo.roulette.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Roulette implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
    private Boolean status;
    private List<Bet> bet = new ArrayList<>();

    public Roulette(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Bet> getBet() {
        return bet;
    }

    public void setBet(List<Bet> bet) {
        this.bet = bet;
    }
}
