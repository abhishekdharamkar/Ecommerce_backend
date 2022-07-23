package com.mb.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "User_Registration")
public class RegisterUser
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

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

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Set<Role> getRole()
	{
		return role;
	}

	public void setRole(Set<Role> role)
	{
		this.role = role;
	}

	public RegisterUser()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString()
	{
		return "RegisterUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}
}
