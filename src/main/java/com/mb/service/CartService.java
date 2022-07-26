package com.mb.service;

import java.util.List;
import com.mb.entity.Product;
import com.mb.model.AddToCartModel;

public interface CartService
{
	public String addTocart(AddToCartModel addToCartModel);

	public List<Product> getAllCartItems();
}
