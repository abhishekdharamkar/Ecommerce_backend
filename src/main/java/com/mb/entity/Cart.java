package com.mb.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cart")
public class Cart
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", nullable = false, updatable = false)
	private Product product;
	// one product are in many users cart or many users has unique product

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private RegisterUser registerUser;

	private Long quantity;

	public Cart()
	{
	}

	public Cart(Long id, Product product, RegisterUser registerUser, Long quantity)
	{
		super();
		this.id = id;
		this.product = product;
		this.registerUser = registerUser;
		this.quantity = quantity;
	}

	public Long getId()
	{
		return id;
	}

	public Product getProduct()
	{
		return product;
	}

	public RegisterUser getUser()
	{
		return registerUser;
	}

	public Long getQuantity()
	{
		return quantity;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public void setUser(RegisterUser registerUser)
	{
		this.registerUser = registerUser;
	}

	public void setQuantity(Long long1)
	{
		this.quantity = long1;
	}

	@Override
	public String toString()
	{
		return "Cart [id=" + id + ", product=" + product + ", user=" + registerUser + ", quantity=" + quantity + "]";
	}

}
