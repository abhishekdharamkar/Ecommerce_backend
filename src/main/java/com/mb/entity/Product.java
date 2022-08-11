package com.mb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.mb.audit.Auditable;

@Entity
@Table(name = "Product_List")
public class Product extends Auditable
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String productname;

	private String productcategory;

	private String productbrand;

	private String productprice;

	private String productinfo;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getProductname()
	{
		return productname;
	}

	public void setProductname(String productname)
	{
		this.productname = productname;
	}

	public String getProductcategory()
	{
		return productcategory;
	}

	public void setProductcategory(String productcategory)
	{
		this.productcategory = productcategory;
	}

	public String getProductbrand()
	{
		return productbrand;
	}

	public void setProductbrand(String productbrand)
	{
		this.productbrand = productbrand;
	}

	public String getProductprice()
	{
		return productprice;
	}

	public void setProductprice(String productprice)
	{
		this.productprice = productprice;
	}

	public String getProductinfo()
	{
		return productinfo;
	}

	public void setProductinfo(String productinfo)
	{
		this.productinfo = productinfo;
	}

	public Product()
	{

	}
}
