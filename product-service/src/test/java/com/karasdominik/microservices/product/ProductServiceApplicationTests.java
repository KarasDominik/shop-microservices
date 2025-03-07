package com.karasdominik.microservices.product;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ProductServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	static {
		mongoDBContainer.start();
	}

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {
		var requestBody = """
				{
				    "id": "1",
				    "name": "iPhone 15",
				    "description": "Phone",
				    "price": 1000
				}
				""";

		given()
				.contentType(JSON)
				.body(requestBody)
			.when()
				.post("/api/v1/products")
			.then()
				.statusCode(201)
				.body("id", notNullValue())
				.body("name", is("iPhone 15"))
				.body("description", is("Phone"))
				.body("price", is(1000));
	}
}
