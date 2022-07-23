package com.mb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Product;
import com.mb.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> search(String keyword)
	{
		if (keyword != null)
		{
			System.out.println("yes");
			return productRepo.search(keyword);
		}
		return productRepo.findAll();
	}

	@Override
	public Product save(Product product)
	{
		System.out.println(product);

		return productRepo.save(product);
	}

	@Override
	public String getDetails(String Product)
	{
		System.out.println(Product);
		return productRepo.getDetails(Product);
	}

}
