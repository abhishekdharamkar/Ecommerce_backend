package com.mb.model;

import javax.validation.constraints.NotBlank;

public class ProductModel
{
	@NotBlank(message = "Name is mandatory")
	private String productname;

	@NotBlank
	private String productcategory;

	@NotBlank
	private String productbrand;

	@NotBlank
	private String productprice;

	@NotBlank
	private String productinfo;

	public String getProductname()
	{
		return productname;
	}

	public String getProductcategory()
	{
		return productcategory;
	}

	public String getProductbrand()
	{
		return productbrand;
	}

	public String getProductprice()
	{
		return productprice;
	}

	public String getProductinfo()
	{
		return productinfo;
	}

	public void setProductname(String productname)
	{
		this.productname = productname;
	}

	public void setProductcategory(String productcategory)
	{
		this.productcategory = productcategory;
	}

	public void setProductbrand(String productbrand)
	{
		this.productbrand = productbrand;
	}

	public void setProductprice(String productprice)
	{
		this.productprice = productprice;
	}

	public void setProductinfo(String productinfo)
	{
		this.productinfo = productinfo;
	}

}
