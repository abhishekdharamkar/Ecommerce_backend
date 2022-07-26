package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>
{
	// public String existsByProductId();
}
