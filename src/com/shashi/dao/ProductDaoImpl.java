package com.shashi.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.ProductBean;
import com.shashi.beans.TransactionBean;
import com.shashi.beans.DemandBean;
import com.shashi.beans.OrderBean;
import com.shashi.beans.OwnerCompanyBean;
import com.shashi.utility.DBUtil;
import com.shashi.utility.IDUtil;
import com.shashi.utility.MailMessage;

public class ProductDaoImpl implements ProductDao {

	@Override
	public String addProduct(String username,String prodName, String prodType, String prodInfo, double prodPrice, int prodQuantity,
			InputStream prodImage,String userName, Long mobileNo, String emailId ) {
		String status = null;
		String prodId = IDUtil.generateId();
        System.out.println(username);
        String s="Admin";
		ProductBean product = new ProductBean(prodId, prodName, prodType, prodInfo, prodPrice, prodQuantity, prodImage);
		if(!(username.equals(s))) {
			System.out.println("enter");
		UserDaoImpl userdao = new UserDaoImpl();
		String email = userdao.getUserAddr(username);
		Long mobile= userdao.getUserMobile(username);
OwnerCompanyBean company= new OwnerCompanyBean(username,mobile,email,prodId, prodName, prodType, prodInfo, prodPrice, prodQuantity,prodImage);
OwnerCompanyDaoImpl OwnerCompany= new OwnerCompanyDaoImpl();
OwnerCompany.addCompany(company);
		
		}else {
			
			OwnerCompanyBean company= new OwnerCompanyBean(userName,mobileNo,emailId,prodId, prodName, prodType, prodInfo, prodPrice, prodQuantity,prodImage);
			OwnerCompanyDaoImpl OwnerCompany= new OwnerCompanyDaoImpl();
			OwnerCompany.addCompany(company);
		}
		status = addProduct(product);
		return status;
	}

	@Override
	public String addProduct(ProductBean product) {
		String status = "Product Registration Failed!";

		if (product.getProdId() == null)
			product.setProdId(IDUtil.generateId());

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("insert into product values(?,?,?,?,?,?,?);");
			ps.setString(1, product.getProdId());
			ps.setString(2, product.getProdName());
			ps.setString(3, product.getProdType());
			ps.setString(4, product.getProdInfo());
			ps.setDouble(5, product.getProdPrice());
			ps.setInt(6, product.getProdQuantity());
			ps.setBlob(7, product.getProdImage());

			int k = ps.executeUpdate();

			if (k > 0) {

				status = "Product Added Successfully with Product Id: " + product.getProdId();

			} else {

				status = "Product Updation Failed!";
			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return status;
	}

	@Override
	public String removeProduct(String prodId) {
		String status = "Product Removal Failed!";
		
		
		OwnerCompanyDaoImpl OwnerCompany= new OwnerCompanyDaoImpl();
		OwnerCompany.removeProduct(prodId);
		Connection con = DBUtil.provideConnection();
		Connection con1 = DBUtil.provideConnection();
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			
			ps = con.prepareStatement("delete from product where prodId=?");
			ps.setString(1, prodId);

			int k = ps.executeUpdate();

			if (k > 0) {
				status = "Product Removed Successfully!";
System.out.println("product removeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				ps2 = con1.prepareStatement("delete from usercart where prodId=?");

				ps2.setString(1, prodId);

				ps2.executeUpdate();

			}

		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(con1);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(ps2);

		return status;
	}

	@Override
	public String updateProduct(ProductBean prevProduct, ProductBean updatedProduct) {
		String status = "Product Updation Failed!";

		if (!prevProduct.getProdId().equals(updatedProduct.getProdId())) {

			status = "Both Products are Different, Updation Failed!";

			return status;
		}

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
					"update product set prodName=?,prodType=?,prodInfo=?,prodPrice=?,prodQuantity=?,prodImage=? where prodId=?");

			ps.setString(1, updatedProduct.getProdName());
			ps.setString(2, updatedProduct.getProdType());
			ps.setString(3, updatedProduct.getProdInfo());
			ps.setDouble(4, updatedProduct.getProdPrice());
			ps.setInt(5, updatedProduct.getProdQuantity());
			ps.setBlob(6, updatedProduct.getProdImage());
			ps.setString(7, prevProduct.getProdId());

			int k = ps.executeUpdate();

			if (k > 0)
				status = "Product Updated Successfully!";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return status;
	}

	@Override
	public String updateProductPrice(String prodId, double updatedPrice) {
		String status = "Price Updation Failed!";

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("update product set pprice=? where prodId=?");

			ps.setDouble(1, updatedPrice);
			ps.setString(2, prodId);

			int k = ps.executeUpdate();

			if (k > 0)
				status = "Price Updated Successfully!";
		} catch (SQLException e) {
			status = "Error: " + e.getMessage();
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return status;
	}

	@Override
	public List<ProductBean> getAllProducts() {
		List<ProductBean> products = new ArrayList<ProductBean>();

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from product");

			rs = ps.executeQuery();

			while (rs.next()) {

				ProductBean product = new ProductBean();

				product.setProdId(rs.getString(1));
				product.setProdName(rs.getString(2));
				product.setProdType(rs.getString(3));
				product.setProdInfo(rs.getString(4));
				product.setProdPrice(rs.getDouble(5));
				product.setProdQuantity(rs.getInt(6));
				product.setProdImage(rs.getAsciiStream(7));

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

	public List<ProductBean> getUserProducts(String username) {

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductBean> products = new ArrayList<ProductBean>();
		try {

			TransDaoImpl trans = new TransDaoImpl();
			List<String> transactions = new ArrayList<String>();
			transactions = trans.getTransId(username);
			for (String transaction : transactions) {
				OrderDaoImpl orderdao = new OrderDaoImpl();

				List<String> prodid = new ArrayList<String>();
				prodid = orderdao.getProdId(transaction);
				for (String order : prodid) {
					ps = con.prepareStatement("select * from product where prodId=?");
					ps.setString(1, order);
					rs = ps.executeQuery();
					while (rs.next()) {

						ProductBean product = new ProductBean();

						product.setProdId(rs.getString(1));
						product.setProdName(rs.getString(2));
						product.setProdType(rs.getString(3));
						product.setProdInfo(rs.getString(4));
						product.setProdPrice(rs.getDouble(5));
						product.setProdQuantity(rs.getInt(6));
						product.setProdImage(rs.getAsciiStream(7));

						products.add(product);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return products;
	}

	/*
	 * Connection con = DBUtil.provideConnection();
	 * 
	 * PreparedStatement ps = null; PreparedStatement ps1 = null; PreparedStatement
	 * ps2 = null; ResultSet rs = null; ResultSet rs1 = null; ResultSet rs2 = null;
	 * OrderDaoImpl orderdao = new OrderDaoImpl();
	 * 
	 * List<OrderBean> orders = new ArrayList<OrderBean>(); orders =
	 * orderdao.getAllOrders();
	 * 
	 * for(OrderBean order: orders){ String transId = order.getTransactionId();
	 * String prodId = order.getProductId(); int quantity = order.getQuantity(); int
	 * shipped = order.getShipped(); String userId = new
	 * TransDaoImpl().getUserId(transId); String userAddr = new
	 * UserDaoImpl().getUserAddr(userId);
	 */
	/*
	 * try { // ps = con.prepareStatement("select * from product where prodId
	 * in(select // prodId from shopping.orders where transId in(select transId from
	 * // shopping.transactions where username=?"); ps =
	 * con.prepareStatement("select transId from transactions where username=?");
	 * ps.setString(1, username);
	 * 
	 * rs = ps.executeQuery(); while (rs.next()) { TransactionBean trans = new
	 * TransactionBean(); String TransactionId = trans.getTransactionId(); ps1 =
	 * con.prepareStatement("select prodId from orders where transId=?");
	 * ps1.setString(1, TransactionId); rs1 = ps1.executeQuery(); while (rs1.next())
	 * { OrderBean order = new OrderBean(); String prodId = order.getProductId();
	 * ps2 = con.prepareStatement("select * from product where prodId=?");
	 * ps2.setString(1, prodId); rs2 = ps2.executeQuery(); while (rs2.next()) {
	 * 
	 * ProductBean product = new ProductBean();
	 * 
	 * product.setProdId(rs2.getString(1)); product.setProdName(rs2.getString(2));
	 * product.setProdType(rs2.getString(3)); product.setProdInfo(rs2.getString(4));
	 * product.setProdPrice(rs2.getDouble(5));
	 * product.setProdQuantity(rs2.getInt(6));
	 * product.setProdImage(rs2.getAsciiStream(7));
	 * 
	 * products.add(product);
	 * 
	 * } } }
	 * 
	 * 
	 * }
	 */ /*
		 * catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * DBUtil.closeConnection(con); DBUtil.closeConnection(ps);
		 * DBUtil.closeConnection(rs);
		 * 
		 * return products;
		 */
	@Override
	public byte[] getImage(String prodId) {
		byte[] image = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select prodImage from product where  prodId=?");

			ps.setString(1, prodId);

			rs = ps.executeQuery();

			if (rs.next())
				image = rs.getBytes("prodImage");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);

		return image;
	}

	@Override
	public ProductBean getProductDetails(String prodId) {
		ProductBean product = null;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from product where prodId=?");

			ps.setString(1, prodId);
			rs = ps.executeQuery();

			while (rs.next()) {
				product = new ProductBean();
				product.setProdId(rs.getString(1));
				product.setProdName(rs.getString(2));
				product.setProdType(rs.getString(3));
				product.setProdInfo(rs.getString(4));
				product.setProdPrice(rs.getDouble(5));
				product.setProdQuantity(rs.getInt(6));
				product.setProdImage(rs.getAsciiStream(7));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return product;
	}

	@Override
	public String updateProductWithoutImage(String prevProductId, ProductBean updatedProduct) {
		String status = "Product Updation Failed!";

		if (!prevProductId.equals(updatedProduct.getProdId())) {

			status = "Both Products are Different, Updation Failed!";

			return status;
		}

		/*
		 * System.out.println("pId: "+updatedProduct.getProdId());
		 * System.out.println("pName: "+updatedProduct.getProdName());
		 * System.out.println("pType: "+updatedProduct.getProdType());
		 * System.out.println("pInfo: "+updatedProduct.getProdInfo());
		 * System.out.println("pPrice: "+updatedProduct.getProdPrice());
		 * System.out.println("pQuantity: "+updatedProduct.getProdQuantity());
		 */

		int prevQuantity = new ProductDaoImpl().getProductQuantity(prevProductId);
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(
					"update product set prodName=?,prodType=?,prodInfo=?,prodPrice=?,prodQuantity=? where prodId=?");

			ps.setString(1, updatedProduct.getProdName());
			ps.setString(2, updatedProduct.getProdType());
			ps.setString(3, updatedProduct.getProdInfo());
			ps.setDouble(4, updatedProduct.getProdPrice());
			ps.setInt(5, updatedProduct.getProdQuantity());
			ps.setString(6, prevProductId);

			int k = ps.executeUpdate();
			// System.out.println("prevQuantity: "+prevQuantity);
			if ((k > 0) && (prevQuantity < updatedProduct.getProdQuantity())) {
				status = "Product Updated Successfully!";
				// System.out.println("updated!");
				List<DemandBean> demandList = new DemandDaoImpl().haveDemanded(prevProductId);

				for (DemandBean demand : demandList) {

					String userFName = new UserDaoImpl().getFName(demand.getUsername());

					MailMessage.productAvailableNow(demand.getUsername(), userFName, updatedProduct.getProdName(),
							prevProductId);

					boolean flag = new DemandDaoImpl().removeProduct(demand.getUsername(), prevProductId);

					if (flag)
						status += " And Mail Send to the customers who were waiting for this product!";
				}
			} else if (k > 0)
				status = "Product Updated Successfully!";
			else
				status = "Product Not available in the store!";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		// System.out.println("Prod Update status : "+status);

		return status;
	}

	@Override
	public double getProductPrice(String prodId) {
		double price = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from product where prodId=?");

			ps.setString(1, prodId);
			rs = ps.executeQuery();

			if (rs.next()) {
				price = rs.getDouble("prodPrice");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return price;
	}

	@Override
	public boolean sellNProduct(String prodId, int n) {
		boolean flag = false;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		try {

			ps = con.prepareStatement("update product set prodQuantity=(prodQuantity - ?) where prodId=?");

			ps.setInt(1, n);

			ps.setString(2, prodId);

			int k = ps.executeUpdate();

			if (k > 0)
				flag = true;
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);

		return flag;
	}

	@Override
	public int getProductQuantity(String prodId) {

		int quantity = 0;

		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("select * from product where prodId=?");

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
