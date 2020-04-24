package com.tyss.dao;

import java.util.List;

import com.tyss.dto.ProductBean;

public interface ProductDAO {

	public boolean createProduct(ProductBean bean);
	public boolean update(ProductBean bean);
	public boolean delete(String productName);
	public ProductBean getProduct(String productName);
	public List<ProductBean> getAllProduct();
	
}
