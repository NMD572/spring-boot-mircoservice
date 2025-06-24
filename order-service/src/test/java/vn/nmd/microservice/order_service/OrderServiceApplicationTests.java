package vn.nmd.microservice.order_service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.testcontainers.containers.MySQLContainer;

import io.restassured.RestAssured;
import vn.nmd.microservice.order_service.stubs.InventoryClientStub;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0) // random port for wire mock server
class OrderServiceApplicationTests {

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
	void shouldPlaceOrder() {
		String requestBody = """
				{
				    "skuCode":"iphone_15",
				    "price":1000,
				    "quantity":1
				}
								""";
		InventoryClientStub.stubInventoryCall("iphone_15",1);
		
		RestAssured.given()
			.contentType("application/json")
			.body(requestBody)
			.when()
			.post("/api/order")
			.then()
			.log().all()
			.statusCode(HttpStatus.CREATED.value())
			.body("id", Matchers.notNullValue())
			.body("orderNumber", Matchers.notNullValue())
			.body("skuCode", Matchers.equalTo("iphone_15"))
			.body("price", Matchers.equalTo(1000))
			.body("quantity", Matchers.equalTo(1));
			
	}

}
