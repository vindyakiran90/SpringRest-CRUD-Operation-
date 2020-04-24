package com.tyss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.dto.ProductBean;
import com.tyss.dto.ProductResponse;
import com.tyss.service.ProductService;

//@Controller
@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(path="/getProduct", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public ProductResponse getProduct(String productName) {
		ProductBean productBean = productService.getProduct(productName);
		ProductResponse productResponse = new ProductResponse();
		if(productBean != null) {
			productResponse.setMessage("Product is present");
			productResponse.setProductInfo(productBean);
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Product name is incorrect");
		}
		return productResponse;
	}
	
	@PostMapping(path="/addProduct", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}, 
										produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public ProductResponse addProduct(@RequestBody ProductBean bean) {
		boolean isAdded = productService.createProduct(bean);
		ProductResponse productResponse = new ProductResponse();
		
		if(isAdded) {
			productResponse.setMessage("Data inserted");
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Data not inserted");
		}
		return productResponse;
	}
	
	@GetMapping(path="/getAllProduct", produces = {MediaType.APPLICATION_JSON_VALUE, 
										 MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public ProductResponse getAllProduct() {
		List<ProductBean> productList =  productService.getAllProduct();
		ProductResponse productResponse = new ProductResponse();
		if(productList != null && !productList.isEmpty()) {
			productResponse.setProductList(productList);
		} else {
			productResponse.setError(true);
			productResponse.setMessage("No data present");
		}
		return productResponse;
		
	}
	
	@DeleteMapping(path="/deleteProduct/{productName}", 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//@ResponseBody
	public ProductResponse deleteProduct(@PathVariable(name="productName") String productName) {
		boolean isDeleted = productService.delete(productName);
		ProductResponse productResponse = new ProductResponse();
		if(isDeleted) {
			productResponse.setMessage("Deleted");
		} else {
			productResponse.setError(true);
			productResponse.setMessage("Not deleted");
		}
		return productResponse;
	}
	
	@PutMapping(path="/updateProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public ProductResponse updateProduct(ProductBean bean) {
		boolean isUpdated = productService.update(bean);
		ProductResponse productResponse = new ProductResponse();
		if(isUpdated) {
			productResponse.setMessage("Updated");
		} else {
			productResponse.setMessage("Not updated");
			productResponse.setError(true);
		}
		return productResponse;
	}
	
}
