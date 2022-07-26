package com.mb.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mb.entity.CustomUserDetails;
import com.mb.entity.RegisterUser;
import com.mb.entity.Role;
import com.mb.exception.UserAllreadyExist;
import com.mb.model.SignInModel;
import com.mb.model.SignUpModel;
import com.mb.repository.RegisterUserRepo;
import com.mb.repository.RoleRepository;
import com.mb.utility.JwtUtil;

@Service
public class RegisterUserServiceImpl implements RegisterUserService
{
	@Autowired
	RegisterUserRepo userrepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleRepository rolerepository;

	@Override
	public RegisterUser registerNewAdmin(SignUpModel adminSignup)
	{
		if (userrepo.existsByEmail(adminSignup.getEmail()))
		{
			throw new UserAllreadyExist("Email Allready Exist in Database");
		}
		else
		{
			// 1--Admin ROle
			RegisterUser user = new RegisterUser();
			Role role = rolerepository.findById((long) 1).get();
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user = modelMapper.map(adminSignup, RegisterUser.class);

			user.setRole(roles);
			user.setPassword(passwordEncoder.encode(adminSignup.getPassword()));
			return userrepo.save(user);
		}
	}

	@Override
	public RegisterUser registerNewUser(SignUpModel userSignup)
	{
		if (userrepo.existsByEmail(userSignup.getEmail()))
		{
			throw new UserAllreadyExist("Email Allready Exist in Database");
		}
		else
		{
			// 2--User ROle
			RegisterUser user = new RegisterUser();
			Role role = rolerepository.findById((long) 2).get();
			Set<Role> roles = new HashSet<>();
			roles.add(role);
			user = modelMapper.map(userSignup, RegisterUser.class);
			user.setRole(roles);
			user.setPassword(passwordEncoder.encode(userSignup.getPassword()));
			return userrepo.save(user);
		}
	}

	// @Override
	// public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	// {
	// final RegisterUser customer = userrepo.findByEmail(username);
	// if (customer == null)
	// {
	// throw new UsernameNotFoundException(username);
	// }
	// UserDetails user = User.withUsername(customer.getEmail())
	// .password(customer.getPassword())
	// .authorities("ROLE_ADMIN").build();
	//
	// System.out.println(customer);
	// System.out.println(username);
	// return user;
	// }

	@Override
	public RegisterUser updateUser(String email, RegisterUser Updateduser)
	{
		RegisterUser currentUser = userrepo.findByEmail(email);
		System.out.println("sssss" + currentUser + "update===" + Updateduser);
		currentUser.setEmail(Updateduser.getEmail());
		return userrepo.save(currentUser);

	}

	// logIn
	@Override
	public Object signIn(SignInModel userLogin)
	{
		Map<String, Object> data = new HashMap<String, Object>();

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtil.generateJwtToken(authentication);

		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		data.put("jwtToken", jwtToken);
		data.put("username", userDetails.getUsername());
		data.put("password", userDetails.getPassword());
		data.put("roles", roles);

		return data;
	}

}
