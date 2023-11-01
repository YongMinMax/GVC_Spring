package com.waveware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.security.auth.login.Configuration;


@SpringBootApplication
public class GvCtestApplication extends SpringBootServletInitializer
{

	public static void main(String[] args)
	{
		SpringApplication.run(GvCtestApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(GvCtestApplication.class);
	}
}
