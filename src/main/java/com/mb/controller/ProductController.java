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

	// Create API to get details of a product.
	@GetMapping("/product/search/getDetails/{name}")
	public String getDetails(@PathVariable(name = "name") String name)
	{
		System.out.println(name);
		String n = productService.getDetails(name);
		System.out.println(n);
		return n;
	}

}
