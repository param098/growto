package com.shashi.dao;

import java.util.List;

public interface TransDao {

	public String getUserId(String transId);
	public List<String> getTransId(String username);
}
