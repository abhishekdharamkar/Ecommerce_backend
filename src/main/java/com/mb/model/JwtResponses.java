package com.mb.model;

public class JwtResponse
{

	private String jwtTokens;

	public JwtResponse()
	{

	}

	public JwtResponse(String jwtTokens)
	{
		super();
		this.jwtTokens = jwtTokens;
	}

	public String getJwtTokens()
	{
		return jwtTokens;
	}

	public void setJwtTokens(String jwtTokens)
	{
		this.jwtTokens = jwtTokens;
	}

}
