package com.atul.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
		System.out.println("Running spring security -->");
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


class Bar {

	private Foo foo;

	Bar(final Foo foo) {
		this.foo = foo;
	}
}



class Foo {

}
