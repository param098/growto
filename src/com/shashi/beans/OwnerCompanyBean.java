package com.shashi.beans;

import java.io.InputStream;

public class OwnerCompanyBean {

	private String userName;
	private Long mobileNo;
	private String emailId;
	private String prodId;
	private String prodName;
	private String prodType;
	private String prodInfo;
	private double prodPrice;
	private int prodQuantity;
	private InputStream prodImage;
	
	public OwnerCompanyBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OwnerCompanyBean(String userName, Long mobileNo, String emailId, String prodId, String prodName,
			String prodType, String prodInfo, double prodPrice, int prodQuantity, InputStream prodImage) {
		super();
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodType = prodType;
		this.prodInfo = prodInfo;
		this.prodPrice = prodPrice;
		this.prodQuantity = prodQuantity;
		this.prodImage = prodImage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public InputStream getProdImage() {
		return prodImage;
	}
	public void setProdImage(InputStream prodImage) {
		this.prodImage = prodImage;
	}
}
