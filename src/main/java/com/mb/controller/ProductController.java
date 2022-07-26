package com.mb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Product;
import com.mb.service.ProductService;

@RestController
@RequestMapping("api/v1")
public class ProductController
{

	@Autowired
	ProductService productService;

	@PostMapping("product/save")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Product saveProduct(@RequestBody Product product)
	{

		return productService.save(product);

	}

	@GetMapping("/product/search/{keyword}")
	public List<Product> Search(@PathVariable(name = "keyword") String keyword)
	{
		System.out.println(keyword);
		List<Product> listProducts = productService.search(keyword);

		return listProducts;
	}

	@GetMapping("product/filter/{min}/{max}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<Product> filterByPrice(@PathVariable int min, @PathVariable int max)
	{
		return productService.filterByPrice(min, max);
	}

	// @GetMapping("/product/filter/ascPrice")
	// public List<Product> AscPrice()
	// {
	//
	// List<Product> listProducts = productService.AscPrice();
	//
	// return listProducts;
	// }
	//
	// @GetMapping("/product/filter/ascPrice")
	// public List<Product> DescPrice()
	// {
	//
	// List<Product> listProducts = productService.DescPrice();
	//
	// return listProducts;
	// }

	// Create API to get details of a product.
	@GetMapping("/product/search/getProductDetails")
	public List<Product> getProductDetails()
	{

		return productService.getProductDetails();
	}

}
