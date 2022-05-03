package com.shashi.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.CartBean;
import com.shashi.beans.OrderBean;
import com.shashi.beans.ProductBean;
import com.shashi.beans.TransactionBean;
import com.shashi.beans.UserBean;
import com.shashi.beans.UserProductBean;
import com.shashi.utility.DBUtil;
import com.shashi.utility.MailMessage;

public class OrderDaoImpl implements OrderDao{

	@Override
	public String paymentSuccess(String username,double paidAmount) {
		String status = "Order Placement Failed!";
		
		
		List<CartBean> cartItems = new ArrayList<CartBean>();
		cartItems = new CartDaoImpl().getAllCartItems(username);
		
		if(cartItems.size()==0)
				return status;
		
		System.out.println("111111111111111111111111");
		TransactionBean transaction = new TransactionBean(username,paidAmount) ;
		System.out.println("2222222222222222222222222");
		
		PreparedStatement ps1 = null;
		//PreparedStatement ps2 = null;
		int p=0,q=0,k=0;
		boolean flag = false;
		
		String transactionId = transaction.getTransactionId();
		System.out.println("333333333333333333333333333333");
		if(transaction != null) {
			//System.out.println("Transaction: "+transaction.getTransactionId()+" "+transaction.getTransAmount()+" "+transaction.getUserName()+" "+transaction.getTransDateTime());
			
			for(CartBean item : cartItems) {
				System.out.println("4444444444444444444");
				double amount = new ProductDaoImpl().getProductPrice(item.getProdId()) * item.getQuantity(); 
				System.out.println("5555555555555555555555555555555");
				ProductBean prod= new ProductDaoImpl().getProductDetails(item.getProdId());
				System.out.println("666666666666666666666666");
				System.out.println(transactionId + item.getProdId() + item.getQuantity()+ amount);
				OrderBean order = new OrderBean(transactionId, item.getProdId(), item.getQuantity(), amount);
				System.out.println(transactionId + item.getProdId() + item.getQuantity()+ amount);
				System.out.println( username);
				System.out.println( item.getProdId());
				System.out.println(  prod.getProdName());
				System.out.println(  prod.getProdType());
				System.out.println( prod.getProdInfo());
				System.out.println( item.getQuantity());
				System.out.println("hello"+ username + item.getProdId()+ prod.getProdName()+ prod.getProdType()+prod.getProdInfo() + amount+ item.getQuantity());
				UserProductBean user = new UserProductBean(username,item.getProdId(),prod.getProdName(),prod.getProdType(),prod.getProdInfo() , amount,item.getQuantity());
				UserProductDaoImpl userprod= new UserProductDaoImpl();
				boolean f= userprod.addOrder(user);
				flag = addOrder(order);
				if(!flag)
					break;
				else {
					flag = new CartDaoImpl().removeAProduct(item.getUsername(), item.getProdId());
				}
				
				if(!flag)
					break;
				else
					flag = new ProductDaoImpl().sellNProduct(item.getProdId(), item.getQuantity());
				
				if(!flag)
					break;
			}
			
			if(flag) {
				flag = new OrderDaoImpl().addTransaction(transaction);
				if(flag) {
					
					MailMessage.transactionSuccess( new UserDaoImpl().getUserAddr(username), new UserDaoImpl().getFName(username), transaction.getTransactionId(), transaction.getTransAmount());
					
					status = "Order Placed Successfully!";
				}
			}
			
		}
		
		
		return status;
	}

	@Override
	public boolean addOrder(OrderBean order) {
		boolean flag= false;
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into orders values(?,?,?,?,?)");
			
			ps.setString(1, order.getTransactionId());
			ps.setString(2, order.getProductId());
			ps.setInt(3, order.getQuantity());
			ps.setDouble(4, order.getAmount());
			ps.setInt(5, 0);
			
			int k = ps.executeUpdate();
			
			if(k>0)
				flag = true;
			
		} catch (SQLException e) {
			flag = false;
			e.printStackTrace();
		}
		
		
		
		return flag;
	}

	@Override
	public boolean addTransaction(TransactionBean transaction) {
		boolean flag = false;
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement("insert into transactions values(?,?,?,?)");
			
			ps.setString(1, transaction.getTransactionId());
			ps.setString(2, transaction.getUsername());
			ps.setTimestamp(3, transaction.getTransDateTime());
			ps.setDouble(4, transaction.getTransAmount());
			
			int k = ps.executeUpdate();
			
			if(k>0)
				flag = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

	@Override
	public int countSoldItem(String prodId) {
		int count = 0;
		
		Connection  con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("select sum(quantity) from orders where prodid=?");
			
			ps.setString(1, prodId);
			
			rs = ps.executeQuery();
			
			if(rs.next())
				count = rs.getInt(1);
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		
		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		
		
		return count;
	}

	@Override
	public List<OrderBean> getAllOrders() {
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		
		Connection con = DBUtil.provideConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select * from orders");
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				
				OrderBean order = new OrderBean(rs.getString("transid"),rs.getString("prodid"),rs.getInt("quantity"),rs.getDouble("amount"),rs.getInt("shipped"));
				
				orderList.add(order);

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return orderList;
	}
	
	public List<String> getProdId(String transId){
		List<String> prodId = new ArrayList();
		Connection con = DBUtil.provideConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			ps = con.prepareStatement("select prodId from Order where transId=?");
			
			ps.setString(1, transId);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				prodId.add(rs.getString(1));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		
		return prodId;
	}

	@Override
	public int getProductQuantity(String prodId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
