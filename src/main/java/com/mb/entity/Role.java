package com.mb.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Role
{
	@Override
	public String toString()
	{
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";
	}

	@Id
	private Long roleId;

	private String roleName;

	public Long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Long roleId)
	{
		this.roleId = roleId;
	}

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

}
