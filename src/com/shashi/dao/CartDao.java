package com.shashi.dao;

import java.util.List;

import com.shashi.beans.CartBean;

public interface CartDao{
		
	public String addProductToCart(String username, String prodId, int prodQty);
	
	public String updateProductToCart(String username, String prodId, int prodQty);
	
	public List<CartBean> getAllCartItems(String username);
	
	public int getCartCount(String username);
	
	public String removeProductFromCart(String username,String prodId);
	
	public boolean removeAProduct(String username,String prodId);
	
}
