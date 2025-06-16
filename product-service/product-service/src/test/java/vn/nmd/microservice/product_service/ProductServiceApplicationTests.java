package vn.nmd.microservice.product_service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;		// the used port of local server (that will be assign in run-time)

	@BeforeEach
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name":"Iphone 16",
				    "description": "Iphone 16 is a product from Apple",
				    "price": 28000000
				}
								""";
		RestAssured.given()
			.contentType("application/json")
			.body(requestBody)
			.when()
			.post("/api/product/create")
			.then()
			.statusCode(HttpStatus.OK.value())
			.body("id", Matchers.notNullValue())
			.body("name", Matchers.equalTo("Iphone 16"))
			.body("description", Matchers.equalTo("Iphone 16 is a product from Apple"))
			.body("price", Matchers.equalTo(28000000));
			
	}

}
