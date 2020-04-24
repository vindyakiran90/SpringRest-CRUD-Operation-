package com.tyss.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Data
@Entity
@Table(name="productBean")
@JsonRootName("productInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBean implements Serializable {
	@Id
	@Column
	private String productName;
	@Column
	private String imageUrl;
	@Column
	private double price;
	@Column
	private String description;
	
}
