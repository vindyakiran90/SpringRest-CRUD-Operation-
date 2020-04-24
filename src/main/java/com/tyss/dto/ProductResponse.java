package com.tyss.dto;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
	private boolean error;
	private String message;
	private ProductBean productInfo;
	private List<ProductBean> productList;
}
