package com.mb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Product_List")
public class Product
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String product_name;

	private String product_category;

	private String product_brand;

	private String product_price;

	private String product_info;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getProduct_name()
	{
		return product_name;
	}

	public void setProduct_name(String product_name)
	{
		this.product_name = product_name;
	}

	public String getProduct_category()
	{
		return product_category;
	}

	public void setProduct_category(String product_category)
	{
		this.product_category = product_category;
	}

	public String getProduct_brand()
	{
		return product_brand;
	}

	public void setProduct_brand(String product_brand)
	{
		this.product_brand = product_brand;
	}

	public String getProduct_price()
	{
		return product_price;
	}

	public void setProduct_price(String product_price)
	{
		this.product_price = product_price;
	}

	public String getProduct_info()
	{
		return product_info;
	}

	public void setProduct_info(String product_info)
	{
		this.product_info = product_info;
	}

	Product()
	{

	}
}
