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
	public List<Product> getProductDetails()
	{
		return productRepo.findAll();
	}

	@Override
	public List<Product> filterByPrice(int min, int max)
	{
		return productRepo.filterByPriceRange(min, max);
	}
	//
	// @Override
	// public List<Product> AscPrice()
	// {
	// return productRepo.AscPrice();
	// }
	//
	// @Override
	// public List<Product> DescPrice()
	// {
	// return productRepo.Price();
	// }

}
