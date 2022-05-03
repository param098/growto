package com.shashi.dao;

import java.util.List;

import com.shashi.beans.OwnerCompanyBean;
import com.shashi.beans.ProductBean;

public interface OwnerCompanyDao {

	public void addCompany(OwnerCompanyBean company);
	
	public List<OwnerCompanyBean> getAllCompanies();
	public OwnerCompanyBean getMyCompany(String username);
	public void removeProduct(String prodId);
	public OwnerCompanyBean getProductDetails(String prodid);
	public void updateProductWithoutImage(String prevProductId, OwnerCompanyBean updatedProduct);
	public int getProductQuantity(String prodId) ;
	public int updateProductQuantity(String prodId) ;
}
