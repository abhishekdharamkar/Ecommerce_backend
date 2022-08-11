package com.mb.service;

import java.util.List;
import com.mb.entity.Product;
import com.mb.model.ProductModel;

public interface ProductService
{

	public List<Product> search(String keyword);

	public Product save(ProductModel product);

	public List<Product> getProductDetails();

	public List<Product> filterByPrice(int max, int min);
	// public List<Product> AscPrice();
	//
	// public List<Product> DescPrice();
}
