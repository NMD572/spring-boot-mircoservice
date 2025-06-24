package vn.nmd.microservice.order_service.stubs;


import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class InventoryClientStub {

	public static void stubInventoryCall(String skuCode, Integer quantity) {
		stubFor(get(urlEqualTo("/api/inventory?skuCode="+skuCode+"&quantity="+quantity))
			.willReturn(aResponse()
					.withStatus(201)
					.withHeader("Content-Type","application/json")
					.withBody("true")));
	}
}
