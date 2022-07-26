package com.mb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Product;
import com.mb.model.AddToCartModel;
import com.mb.service.CartService;

@RestController
@RequestMapping("/api/v1")
public class CartController
{

	@Autowired
	private CartService cartServiceImpl;

	@PostMapping("cart/addToCart")
	public String addTocart(@RequestBody AddToCartModel addToCartModel)
	{
		return cartServiceImpl.addTocart(addToCartModel);
	}

	@PostMapping("cart/getProductList")
	public List<Product> getcart()
	{
		return cartServiceImpl.getAllCartItems();

	}
}
