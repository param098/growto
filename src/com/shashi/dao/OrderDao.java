package com.shashi.dao;

import java.util.List;

import com.shashi.beans.OrderBean;
import com.shashi.beans.TransactionBean;

public interface OrderDao {
	
	public String paymentSuccess(String username,double paidAmount);

	public boolean addOrder(OrderBean order);
	
	public boolean addTransaction(TransactionBean transaction);
	
	public int countSoldItem(String prodId);
	
	public List<OrderBean> getAllOrders();
	
	public List<String> getProdId(String transId);
	public int getProductQuantity(String prodId);
}
