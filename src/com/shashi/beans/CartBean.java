package com.shashi.beans;

import java.io.Serializable;

public class CartBean implements Serializable{

	public CartBean() {}
	
	public String username;
	
	public String prodId;
	
	public int quantity;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CartBean(String username, String prodId, int quantity) {
		super();
		this.username = username;
		this.prodId = prodId;
		this.quantity = quantity;
	}
	
	
	
}
