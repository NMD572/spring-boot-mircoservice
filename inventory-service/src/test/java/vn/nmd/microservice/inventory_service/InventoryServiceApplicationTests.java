package vn.nmd.microservice.inventory_service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mysqlContainer = new MySQLContainer("mysql:8.1.0");

	@LocalServerPort
	private Integer port;		// value of {local.server.port}. The used port of local server (that will be assign in run-time)

	@BeforeEach
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	static {
		mysqlContainer.start();
	}
	
	@Test
	void shouldCheckInUse() {
		Boolean inStockReponse = RestAssured.given()
				.contentType("application/json")
				.get("/api/inventory?skuCode=iphone_15&quantity=10")
				.then()
				.log().all()
				.statusCode(HttpStatus.CREATED.value())
				.extract().response().as(Boolean.class);
		assertTrue(inStockReponse);
		
		Boolean notInStockReponse = RestAssured.given()
				.contentType("application/json")
				.get("/api/inventory?skuCode=iphone_15&quantity=101")
				.then()
				.log().all()
				.statusCode(HttpStatus.CREATED.value())
				.extract().response().as(Boolean.class);
		assertFalse(notInStockReponse);
		
	}

}
