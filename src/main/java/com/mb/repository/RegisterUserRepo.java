package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.entity.RegisterUser;

public interface RegisterUserRepo extends JpaRepository<RegisterUser, Long>
{
	public boolean existsByEmail(String email);

	public RegisterUser findByEmail(String username);
}
