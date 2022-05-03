package com.shashi.beans;

public class UserProductBean {

	private String userName;
	private String prodId;
	private String prodName;
	private String prodType;
	private String prodInfo;
	private double prodPrice;
	private int prodQuantity;
	
	
	public UserProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserProductBean(String userName, String prodId, String prodName, String prodType, String prodInfo,
			double prodPrice, int prodQuantity) {
		super();
		this.userName = userName;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodType = prodType;
		this.prodInfo = prodInfo;
		this.prodPrice = prodPrice;
		this.prodQuantity = prodQuantity;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(String prodInfo) {
		this.prodInfo = prodInfo;
	}
	public double getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(double prodPrice) {
		this.prodPrice = prodPrice;
	}
	public int getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(int prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	
	
}
