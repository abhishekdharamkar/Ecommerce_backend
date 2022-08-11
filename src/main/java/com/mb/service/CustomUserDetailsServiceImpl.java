package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mb.entity.CustomUserDetails;
import com.mb.entity.RegisterUser;
import com.mb.repository.RegisterUserRepo;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService
{

	@Autowired
	private RegisterUserRepo userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		RegisterUser registerUser = null;

		registerUser = userRepository.findByEmail(username);
		if (registerUser != null)
		{
			return CustomUserDetails.build(registerUser);
		}
		else
		{
			throw new UsernameNotFoundException(username + " not found");
		}
	}

}
