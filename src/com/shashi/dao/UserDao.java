package com.shashi.dao;

import java.util.List;

import com.shashi.beans.CartBean;
import com.shashi.beans.UserBean;

public interface UserDao {
	
	/*private String userName;
	private Long mobileNo;
	private String username;
	private String address;
	private int pinCode;
	private String password;
	*/
	
	public String registerUser(String userName,Long mobileNo,String username,String address,int pinCode,String password, String userType);
	
	public String registerUser(UserBean user);
	
	public boolean isRegistered(String username);
		
	public String isValidCredential(String username, String password );
	
	public UserBean getUserDetails(String username,String password);
	
	public String getFName(String username);
	
	public String getUserAddr(String userId);
	
	public Long getUserMobile(String userId);
	
	
}
