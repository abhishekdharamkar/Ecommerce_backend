package com.mb.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController
{

	@GetMapping("/all") // all method
	public String checkAllAccess()
	{
		return "All Access Area";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER')") // no access // login
	public String getUserAccess()
	{
		return "authenticated user Access Area";
	}

	@GetMapping("/admin") // no access
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String getAdminAccess()
	{
		return "authenticated admin access Area";
	}

}
