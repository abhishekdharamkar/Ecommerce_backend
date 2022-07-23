package com.mb.service;

import com.mb.entity.RegisterUser;
import com.mb.model.JwtRequest;

public interface RegisterUserService

{
	// Save operation
	RegisterUser registerNewUser(RegisterUser registerUser);

	// update
	RegisterUser updateUser(String email, RegisterUser user);

	RegisterUser registerNewAdmin(RegisterUser registerUser);

	Object signIn(JwtRequest userLogin);

}
