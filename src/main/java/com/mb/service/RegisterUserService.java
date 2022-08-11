package com.mb.service;

import com.mb.entity.RegisterUser;
import com.mb.model.SignInModel;
import com.mb.model.SignUpModel;

public interface RegisterUserService

{
	// Save operation
	RegisterUser registerNewUser(SignUpModel registerUser);

	// update
	RegisterUser updateUser(String email, RegisterUser registerUser);

	RegisterUser registerNewAdmin(SignUpModel signUpModel);

	Object signIn(SignInModel userLogin);

}
