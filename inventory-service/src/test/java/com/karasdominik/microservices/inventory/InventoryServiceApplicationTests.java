package com.karasdominik.microservices.inventory;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	private static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.3.0");
	@LocalServerPort
	private int port;

	static {
		mySQLContainer.start();
	}

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCheckIfIsInInventory() {
		var response = given()
				.queryParam("skuCode", "iphone_15")
				.queryParam("quantity", 10)
				.when()
				.get("/api/v1/inventory")
				.then()
				.statusCode(200)
				.extract().response().as(Boolean.class);

		Assertions.assertTrue(response);
	}

}
