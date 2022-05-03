package com.shashi.dao;

import java.util.List;

import com.shashi.beans.DemandBean;

public interface DemandDao {
	
	public boolean addProduct(String username, String prodId, int demandQty);
	
	public boolean addProduct(DemandBean userDemandBean);
	
	public boolean removeProduct(String username, String prodId);
	
	public List<DemandBean> haveDemanded(String prodId);
	
}
