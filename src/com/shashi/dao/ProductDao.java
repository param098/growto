package com.shashi.dao;

import java.io.InputStream;
import java.util.List;

import com.shashi.beans.ProductBean;

public interface ProductDao {

	public String addProduct(String username,String prodName,String prodType,String prodInfo, double prodPrice, int prodQuantity, InputStream prodImage,String userName, Long mobileNo, String emailId);
	
	public String addProduct(ProductBean product);
	
	public String removeProduct(String prodId);
	
	public String updateProduct(ProductBean prevProduct, ProductBean updatedProduct);
	
	public String updateProductPrice(String prodId, double updatedPrice);
	
	public List<ProductBean> getAllProducts();
	
	public byte[] getImage(String prodId);
	
	public ProductBean getProductDetails(String prodId);
	
	public String updateProductWithoutImage(String prevProductId, ProductBean updatedProduct);
	
	public double getProductPrice(String prodId);
	
	public boolean sellNProduct(String prodId,int n);
	
	public int getProductQuantity(String prodId);
	
	public List<ProductBean> getUserProducts(String username);
}
