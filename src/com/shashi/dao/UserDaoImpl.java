package com.shashi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.CartBean;
import com.shashi.beans.UserBean;
import com.shashi.constants.IUserConstants;
import com.shashi.utility.DBUtil;
import com.shashi.utility.MailMessage;

public class UserDaoImpl implements UserDao {

	@Override
	public String registerUser(String userName, Long mobileNo, String username, String address,int pinCode,
			String password, String userType) {
		
		
		UserBean user = new UserBean(userName,mobileNo,username,address,pinCode,password,userType);
		
		String status = registerUser(user);
		
		return status;
	}

	@Override
	public String registerUser(UserBean user) {
		
		String status = "User Registration Failed!";
		
		boolean isRegtd = isRegistered(user.getUserName());
		
		
		if(isRegtd) {
			status = "Email Id Already Registered!";
			return status;
		}
		Connection conn = DBUtil.provideConnection();
		PreparedStatement ps = null;
		if(conn != null) {
			System.out.println("Connected Successfully!");
		}
			
		try {
			
			ps = conn.prepareStatement("insert into "+IUserConstants.TABLE_USER+" values(?,?,?,?,?,?,?)");
			
			ps.setString(1, user.getUserName());
			ps.setLong(2, user.getMobileNo());
			ps.setString(3, user.getEmailId());
			ps.setString(4, user.getAddress());
			ps.setInt(5, user.getPinCode());
			ps.setString(6, user.getPassword());
			ps.setString(7, user.getUserType());
			int k = ps.executeUpdate();
			
			if(k>0) {
				status = "User Registered Successfully!";
				MailMessage.registrationSuccess(user.getEmailId(), user.getUserName().split(" ")[0]);
			}
			
		} catch (SQLException e) {
			status = "Error: "+e.getMessage();
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(ps);
		
		return status;
	}

	@Override
	public boolean isRegistered(String username) {
		boolean flag = false;
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps =  null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from user where username=?");
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if(rs.next())
				flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		
		return flag;
	}


	@Override
	public String isValidCredential(String username, String password) {
		String status = "Login Denied! Incorrect username or Password";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from user where username=? and password=? ");
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
			
				status = "valid";
				System.out.println("user loginnnnnnnnnnnnnn");
			}
			
		} catch (SQLException e) {
			status = "Error: "+e.getMessage();
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		return status;
	}

	@Override
	public UserBean getUserDetails(String username, String password) {
		
		UserBean user = null;
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new UserBean();
				user.setUserName(rs.getString("userName"));
				user.setMobileNo(rs.getLong("mobileNo"));
				user.setEmailId(rs.getString("emailId"));
				user.setAddress(rs.getString("address"));
				user.setPinCode(rs.getInt("pincode"));
				user.setPassword(rs.getString("password"));
				user.setUserType(rs.getString("usertype"));
				System.out.println("2222222222");
				
				return user;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		
		
		return user;
	}

	@Override
	public String getFName(String username) {
		String fname = "";
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps=null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select userName from user where username=?");
			ps.setString(1, username);
			
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				fname = rs.getString(1);
				
				fname = fname.split(" ")[0];
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return fname;
	}

	@Override
	public String getUserAddr(String userId) {
		String userAddr = "";
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select emailId from user where username=?");
			
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
				userAddr = rs.getString(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return userAddr;
	}
	
	@Override
	public Long getUserMobile(String userId) {
		Long mobile = null;
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select mobileNo from user where username=?");
			
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
				mobile = rs.getLong(1);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return mobile;
	}

	

}
