package com.shashi.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.shashi.utility.IDUtil;

public class TransactionBean implements Serializable{
	
	private String transactionId;
	
	private String username;
	
	private Timestamp transDateTime;
	
	private double transAmount;

	
	public TransactionBean() {
		super();
		this.transactionId = IDUtil.generateTransId();
		
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		sdf.format(timestamp);
		
		this.transDateTime = timestamp;
	}

	
	
	public TransactionBean(String username, double transAmount) {
		super();
		this.username = username;
		this.transAmount = transAmount;
		
		this.transactionId = IDUtil.generateTransId();
		
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		sdf.format(timestamp);
		
		//System.out.println(timestamp);
		
		this.transDateTime = timestamp;
		
	}



	public TransactionBean(String transactionId, String username, double transAmount) {
		super();
		this.transactionId = transactionId;
		this.username = username;
		this.transAmount = transAmount;
		
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		sdf.format(timestamp);
		
		this.transDateTime = timestamp;
	}



	public TransactionBean(String username, Timestamp transDateTime, double transAmount) {
		super();
		this.username = username;
		this.transDateTime = transDateTime;
		this.transactionId = IDUtil.generateTransId();
		this.transAmount = transAmount;
	}

	public TransactionBean(String transactionId, String username, Timestamp transDateTime, double transAmount) {
		super();
		this.transactionId = transactionId;
		this.username = username;
		this.transDateTime = transDateTime;
		this.transAmount = transAmount;

	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getTransDateTime() {
		return transDateTime;
	}

	public void setTransDateTime(Timestamp transDateTime) {
		this.transDateTime = transDateTime;
	}

	public double getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	
	
	
}
