package com.mb.exception;

public class UserAllreadyExist extends RuntimeException
{
	private String message;

	public UserAllreadyExist(String message)
	{
		super(message);
		this.message = message;
	}

	public UserAllreadyExist()
	{
	}
}
