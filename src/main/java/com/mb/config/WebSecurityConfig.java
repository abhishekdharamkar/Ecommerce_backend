package com.mb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.mb.service.CustomUserDetailsServiceImpl;
import com.mb.utility.AuthTokenFilter;
import com.mb.utility.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Bean
	public AuthTokenFilter authTokenFilter()
	{
		return new AuthTokenFilter();
	}

	@Autowired
	private CustomUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtutil;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		// {
		// http.cors().and().csrf().disable().authorizeRequests()
		// .antMatchers("/").permitAll()
		// .antMatchers("/Adminregister", "/Userregister", "/login/**").permitAll()
		// .anyRequest()
		// .authenticated()
		// .and()
		// .sessionManagement()
		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		/*
		 * http.cors().and().csrf().disable()
		 * // .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		 * // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		 * .authorizeRequests()
		 * .antMatchers("/employee/admin").hasRole("ADMIN")
		 * .antMatchers("/employee/user").hasAnyRole("ADMIN","USER")
		 * .antMatchers("/api/v1/login").permitAll()
		 * .antMatchers("/all").permitAll()
		 * .antMatchers("/api/v1/signup/admin").permitAll()
		 * .antMatchers("/api/v1/signup/user").permitAll()
		 * // .antMatchers("/api/v1/product/search/**").permitAll()
		 * .anyRequest().authenticated();
		 * http.addFilterBefore(authTokenFiltera(), UsernamePasswordAuthenticationFilter.class);
		 */
		http.cors().and().csrf().disable()
				// .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				// .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/api/v1/login").permitAll()
				.antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/api/v1/signup/admin").permitAll()
				.antMatchers("/api/v1/signup/user").permitAll()
				.anyRequest().authenticated();

		http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public DaoAuthenticationProvider authProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	// @Override
	// public void configure(WebSecurity web) throws Exception
	// {
	// web.ignoring().antMatchers("/v2/api-docs",
	// "/configuration/ui",
	// "/swagger-resources/**",
	// "/configuration/security",
	// "/swagger-ui.html",
	// "/webjars/**");
	// }

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	{
		auth.authenticationProvider(authProvider());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}
}
