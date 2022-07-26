package com.mb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Cart;
import com.mb.entity.Product;
import com.mb.entity.RegisterUser;
import com.mb.exception.CustomException;
import com.mb.model.AddToCartModel;
import com.mb.repository.CartRepository;
import com.mb.repository.ProductRepository;
import com.mb.repository.RegisterUserRepo;

@Service
public class CartServiceImpl implements CartService
{
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private RegisterUserRepo userRepository;

	@Override
	public String addTocart(AddToCartModel addToCartModel)
	{
		if (cartRepository.existsById(null))
			System.out.println(addToCartModel.getProductId());
		Optional<Product> optionalProduct = productRepository.findById(addToCartModel.getProductId());
		Optional<RegisterUser> optionalUser = userRepository.findById(addToCartModel.getUserId());
		System.out.println(optionalProduct);
		if (!optionalUser.isPresent())
		{
			throw new CustomException("User not exists");
		}
		if (!optionalProduct.isPresent())
		{
			throw new CustomException("Product not found");
		}
		// if (cartRepository.existsByProductId(addToCartModel.getProductId()))
		// {
		// throw new CustomException("Product already in cart");
		// }

		Product product = optionalProduct.get();
		System.out.println(optionalProduct.get().getId());
		RegisterUser user = optionalUser.get();
		Cart cart = new Cart();
		cart.setUser(user);
		cart.setProduct(product);
		cart.setQuantity(addToCartModel.getQuantity());
		cartRepository.save(cart);
		return "add to cart work from service";
	}

	@Override
	public List<Product> getAllCartItems()
	{
		List<Product> cartItemsList = new ArrayList<>();
		List<Cart> cartList = cartRepository.findAll();
		for (int idx = 0; idx < cartList.size(); idx++)
		{
			Product product = cartList.get(idx).getProduct();
			cartItemsList.add(product);
		}
		return cartItemsList;
	}

}
