package com.atul.security.controller;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void test_GetBook() {
        final Response response = given()
                .accept("application/json")
                .when()
                .get("/books/1449374646")
                .then()
                .statusCode(200)
                .extract()
                .response();

        System.out.println(response.asString());
    }
}