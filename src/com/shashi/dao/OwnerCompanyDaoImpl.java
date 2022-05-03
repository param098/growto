package com.shashi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.DemandBean;
import com.shashi.beans.OwnerCompanyBean;
import com.shashi.beans.ProductBean;
import com.shashi.utility.DBUtil;
import com.shashi.utility.MailMessage;

public class OwnerCompanyDaoImpl {

	public void addCompany(OwnerCompanyBean company) {

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("insert into ownercompany values(?,?,?,?,?,?,?,?,?,?);");
			ps.setString(1, company.getUserName());
			ps.setLong(2, company.getMobileNo());
			ps.setString(3, company.getEmailId());
			ps.setString(4, company.getProdId());
			ps.setString(5, company.getProdName());
			ps.setString(6, company.getProdType());
			ps.setString(7, company.getProdInfo());
			ps.setDouble(8, company.getProdPrice());
			ps.setInt(9, company.getProdQuantity());
			ps.setBlob(10, company.getProdImage());

			ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
	}

	public List<OwnerCompanyBean> getAllCompanies() {
		List<OwnerCompanyBean> company = new ArrayList<OwnerCompanyBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from OwnerCompany");

			rs = ps.executeQuery();

			while (rs.next()) {

				OwnerCompanyBean product = new OwnerCompanyBean();

				product.setUserName(rs.getString(1));
				product.setMobileNo(rs.getLong(2));
				product.setEmailId(rs.getString(3));
				product.setProdId(rs.getString(4));
				product.setProdName(rs.getString(5));
				product.setProdType(rs.getString(6));
				product.setProdInfo(rs.getString(7));
				product.setProdPrice(rs.getDouble(8));
				product.setProdQuantity(rs.getInt(9));
				product.setProdImage(rs.getAsciiStream(10));

				company.add(product);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return company;
	}
	
	public OwnerCompanyBean getMyCompany(String username) {
		OwnerCompanyBean product = new OwnerCompanyBean();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from OwnerCompany where username=?");
			ps.setString(1, username);
			rs = ps.executeQuery();

			while (rs.next()) {

				

				product.setUserName(rs.getString(1));
				product.setMobileNo(rs.getLong(2));
				product.setEmailId(rs.getString(3));
				product.setProdId(rs.getString(4));
				product.setProdName(rs.getString(5));
				product.setProdType(rs.getString(6));
				product.setProdInfo(rs.getString(7));
				product.setProdPrice(rs.getDouble(8));
				product.setProdQuantity(rs.getInt(9));
				product.setProdImage(rs.getAsciiStream(10));

			

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return product;
	}
	
	public void removeProduct(String prodId) {
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		

		try {
			ps = con.prepareStatement("delete from OwnerCompany where companyId=?");
			ps.setString(1, prodId);

			int k = ps.executeUpdate();

System.out.println("removeeeeeeeeeeeeeeeeeeeeeeeeeee");
		

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
	

	
	}
	
	@SuppressWarnings("null")
	public OwnerCompanyBean getProductDetails(String prodid) {
		OwnerCompanyBean product = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from Ownercompany where companyId=?");

			ps.setString(1, prodid);
			rs = ps.executeQuery();

			while (rs.next()) {
				
				product.setUserName(rs.getString(1));
				product.setMobileNo(rs.getLong(2));
				product.setEmailId(rs.getString(3));
				product.setProdId(rs.getString(4));
				product.setProdName(rs.getString(5));
				product.setProdType(rs.getString(6));
				product.setProdInfo(rs.getString(7));
				product.setProdPrice(rs.getDouble(8));
				product.setProdQuantity(rs.getInt(9));
				product.setProdImage(rs.getAsciiStream(10));
			}
System.out.println("tryyyyyyyyyyyyyy");
		} catch (SQLException e) {
			System.out.println("catchhhhhhhhhhhhhhh");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return product;
	}
	
	public void updateProductWithoutImage(String prevProductId, OwnerCompanyBean updatedProduct) {
	

		if (prevProductId.equals(updatedProduct.getProdId())) {

			
		


		int prevQuantity = new OwnerCompanyDaoImpl().getProductQuantity(prevProductId);
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
					"update ownerCompany set companyName=?,companyType=?,companyInfo=?,companyPrice=?,companyQuantity=? where companyId=?");

			ps.setString(1, updatedProduct.getProdName());
			ps.setString(2, updatedProduct.getProdType());
			ps.setString(3, updatedProduct.getProdInfo());
			ps.setDouble(4, updatedProduct.getProdPrice());
			ps.setInt(5, updatedProduct.getProdQuantity());
			ps.setString(6, prevProductId);

			 ps.executeUpdate();
			// System.out.println("prevQuantity: "+prevQuantity);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		// System.out.println("Prod Update status : "+status);
		}
		
	}
	
	public int getProductQuantity(String prodId) {

		int quantity = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from ownerCompany where companyId=?");

			ps.setString(1, prodId);
			rs = ps.executeQuery();

			if (rs.next()) {
				quantity = rs.getInt("prodQuantity");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return quantity;
	}

	public int updateProductQuantity(String prodId) {
		int quantity = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from ownerCompany where companyId=?");

			ps.setString(1, prodId);
			rs = ps.executeQuery();

			if (rs.next()) {
				quantity = rs.getInt("prodQuantity");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return quantity;
	}
}
