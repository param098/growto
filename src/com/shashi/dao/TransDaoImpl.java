package com.shashi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.utility.DBUtil;

public class TransDaoImpl implements TransDao{

	@Override
	public String getUserId(String transId) {
		String userId = "";
		
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select username from transactions where transid=?");
			
			ps.setString(1, transId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
				userId = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		return userId;
	}
	
	@Override
	public List<String> getTransId(String username) {
		
		List<String> transId = new ArrayList();
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select transId from transactions where username=?");
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				transId.add(rs.getString(1));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		return transId;
	}

}
