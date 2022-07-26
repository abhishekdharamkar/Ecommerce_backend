package com.mb.model;

public class AddToCartModel
{
	private Long userId;
	private Long productId;
	private Long quantity;

	public AddToCartModel()
	{
	}

	public AddToCartModel(Long userId, Long productId, Long quantity)
	{
		super();
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getUserId()
	{
		return userId;
	}

	public Long getProductId()
	{
		return productId;
	}

	public Long getQuantity()
	{
		return quantity;
	}

}
