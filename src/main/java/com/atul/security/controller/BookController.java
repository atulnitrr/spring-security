package com.atul.security.controller;

import java.security.Principal;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class BookController {


    private RestTemplate restTemplate;

    public BookController(final RestTemplate template) {
        this.restTemplate = template;
    }

    @GetMapping("/greeting")
    public String getString(final Principal principal) {
        return "Hello " + principal.getName() + " !";
    }

    @GetMapping("/books/{isbn}")
    public String findBookByISBN(@PathVariable("isbn") final String isbn) {
       final ResponseEntity<String> responseEntity =
        restTemplate.exchange("https://www.googleapis.com/books/v1/volumes?q=isbn:"+isbn, HttpMethod.GET, null,
                String.class);
       return responseEntity.getBody();
    }


}
