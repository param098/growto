package com.shashi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.OrderBean;
import com.shashi.beans.UserProductBean;
import com.shashi.utility.DBUtil;

public class UserProductDaoImpl {

	public List<UserProductBean> getUserProducts(String username){
		List<UserProductBean> products = new ArrayList<UserProductBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from userproduct where username=?");
			ps.setString(1, username);

			rs = ps.executeQuery();

			while (rs.next()) {

				UserProductBean product = new UserProductBean();

				product.setUserName(rs.getString(1));
				product.setProdId(rs.getString(2));
				product.setProdName(rs.getString(3));
				product.setProdType(rs.getString(4));
				product.setProdInfo(rs.getString(5));
				product.setProdPrice(rs.getDouble(6));
				product.setProdQuantity(rs.getInt(7));
				

				products.add(product);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return products;
	}
	
	public boolean addOrder(UserProductBean order) {
		boolean flag= false;
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into userproduct values(?,?,?,?,?,?,?)");
			
			ps.setString(1, order.getUserName());
			ps.setString(2, order.getProdId());
			ps.setString(3, order.getProdName());
			ps.setString(4, order.getProdType());
			ps.setString(5, order.getProdInfo());
			ps.setDouble(6, order.getProdPrice());
			ps.setInt(7, order.getProdQuantity());
		;
			
			int k = ps.executeUpdate();
			
			if(k>0)
				flag = true;
			
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		
		
		
		return flag;
	}

	
	public String removeProduct(String prodId) {
		String status = "Product Removal Failed!";
		
		
		OwnerCompanyDaoImpl OwnerCompany= new OwnerCompanyDaoImpl();
		OwnerCompany.updateProductQuantity(prodId);
		Connection con = DBUtil.provideConnection();
		Connection con1 = DBUtil.provideConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			
			ps = con.prepareStatement("delete from userproduct where prodId=?");
			ps.setString(1, prodId);

			int k = ps.executeUpdate();

			

			

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		//DBUtil.closeConnection(con1);
		DBUtil.closeConnection(ps);
		//DBUtil.closeConnection(ps2);

		return status;
	}

}
