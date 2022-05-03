package com.shashi.beans;

import java.io.Serializable;

public class DemandBean implements Serializable{
	
	private String username ;
	private String prodId;
	private int demandQty;
	
	public DemandBean() {
		super();
	}

	public DemandBean(String username, String prodId, int demandQty) {
		super();
		this.username = username;
		this.prodId = prodId;
		this.demandQty = demandQty;
	}

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

	public int getDemandQty() {
		return demandQty;
	}

	public void setDemandQty(int demandQty) {
		this.demandQty = demandQty;
	}
	
	
	
}
