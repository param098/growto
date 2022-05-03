package com.shashi.dao;

import java.util.List;

import com.shashi.beans.UserProductBean;

public interface UserProductDao {

	List<UserProductBean> getUserProducts(String username);
	public boolean addOrder(UserProductBean order) ;
	public String removeProduct(String prodId) ;
}
