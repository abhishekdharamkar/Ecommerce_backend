package com.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.RegisterUser;
import com.mb.model.JwtRequest;
import com.mb.response.SuccessResponse;
import com.mb.service.RegisterUserService;
import com.mb.utility.JwtUtil;

@RestController
@RequestMapping("api/v1")
public class SignupController
{
	@Autowired
	private JwtUtil jwtUtility;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RegisterUserService registerUserservice;

	@PostMapping("/signup/admin")
	public ResponseEntity<SuccessResponse> registerNewAdmin(@RequestBody RegisterUser registerUser)
	{
		SuccessResponse responseModel = SuccessResponse.getInstance();
		responseModel.setData(registerUserservice.registerNewAdmin(registerUser));
		responseModel.setMessage("User Registered Successfully");
		responseModel.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<SuccessResponse>(responseModel, HttpStatus.ACCEPTED);
	}

	@PostMapping("/signup/user")
	public ResponseEntity<SuccessResponse> registerNewUser(@RequestBody RegisterUser registerUser)
	{
		SuccessResponse responseModel = SuccessResponse.getInstance();
		responseModel.setData(registerUserservice.registerNewUser(registerUser));
		responseModel.setMessage("User Registered Successfully");
		responseModel.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<SuccessResponse>(responseModel, HttpStatus.ACCEPTED);
	}

	@PostMapping("/login")
	public ResponseEntity<SuccessResponse> signIn(@RequestBody JwtRequest jwtRequest)
	{
		SuccessResponse responseModel = SuccessResponse.getInstance();
		responseModel.setData(registerUserservice.signIn(jwtRequest));
		responseModel.setMessage("User Login Successfully");
		responseModel.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<SuccessResponse>(responseModel, HttpStatus.ACCEPTED);

		// System.out.println("hey" + jwtRequest.getRoleid() + " ----- "); // it showing 0 after passing 1 and 2

	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String getUserAccess()
	{
		return "authenticated user Access Area";
	}

	/*
	 * Create API to update the user email/mobile.
	 */
	@PutMapping("registerUser/{email}")
	public RegisterUser UpdateUser(@PathVariable(name = "email") String email, @RequestBody RegisterUser user)
	{

		return registerUserservice.updateUser(email, user);
	}
}
