package com.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Role;
import com.mb.repository.RoleRepository;

@Service
public class RoleService
{

	@Autowired
	private RoleRepository roleDao;

	public Role createNewRole(Role role)
	{
		return roleDao.save(role);
	}
}
