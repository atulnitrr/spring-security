package com.atul.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		System.out.println("Running spring security -->");
	}


	@Bean
	UserDetailsManager manager() {
		return new InMemoryUserDetailsManager();
	}

	@Bean
	InitializingBean initializingBean(final UserDetailsManager manager) {
		return () -> {
			UserDetails user = User.withDefaultPasswordEncoder().username("atul").password("123").roles("USER").build();
			manager.createUser(user);
		};
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	Foo foo() {
		return new Foo();
	}

	@Bean
	Bar bar(Foo foo) {
		return new Bar(foo);
	}

}


@Component
class UUID {

	public String getUUID() {
		return java.util.UUID.randomUUID().toString();
	}
}


@Component
class Bar1 {

	private Foo foo;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	Bar1(final Foo foo, @Value("#{ UUID.getUUID() }") String uuid) {
		this.foo = foo;
		logger.info("uuid " +  uuid);
	}
}

class Bar {

	private Foo foo;

	Bar(final Foo foo) {
		this.foo = foo;
	}
}



class Foo {

}
