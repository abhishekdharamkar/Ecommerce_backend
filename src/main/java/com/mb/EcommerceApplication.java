package com.mb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.mb.audit.AuditorAwareImpl;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class EcommerceApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware()
	{
		return new AuditorAwareImpl();
	}
}
