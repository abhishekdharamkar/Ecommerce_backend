package com.mb.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.mb.entity.Role;

public class SignUpModel
{
	@NotEmpty
	@Size(min = 2, message = "Name contain atleast 2 character")
	// @ValidName
	private String firstName;
	@NotEmpty
	@Size(min = 2, message = "Name contain atleast 2 character")
	// @ValidName
	private String lastName;
	@Column(unique = true)
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "User_Role", joinColumns = @JoinColumn(name = "User_ID"), inverseJoinColumns = @JoinColumn(name = "Role_ID"))
	private Set<Role> role;

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public Set<Role> getRole()
	{
		return role;
	}

}
