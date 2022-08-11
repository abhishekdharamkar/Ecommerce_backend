package com.mb.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Product;
import com.mb.model.ProductModel;
import com.mb.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMapper;

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
	public Product save(ProductModel product)
	{
		Product p = new Product();
		p = modelMapper.map(product, Product.class);
		return productRepo.save(p);
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

}
