package com.tyss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.dao.ProductDAO;
import com.tyss.dto.ProductBean;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	private ProductDAO dao; 
			
	@Override
	public boolean createProduct(ProductBean bean) {
		return dao.createProduct(bean);
	}

	@Override
	public boolean update(ProductBean bean) {
		return dao.update(bean);
	}

	@Override
	public boolean delete(String productName) {
		return dao.delete(productName);
	}

	@Override
	public ProductBean getProduct(String productName) {
		return dao.getProduct(productName);
	}

	@Override
	public List<ProductBean> getAllProduct() {
		return dao.getAllProduct();
	}

}
