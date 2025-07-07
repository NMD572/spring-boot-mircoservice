package vn.nmd.microservice.api_gateway.route;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

	@Value("${product.service.url}")
	private String productServiceUrl;

	@Value("${order.service.url}")
	private String orderServiceUrl;

	@Value("${inventory.service.url}")
	private String inventoryServiceUrl;

	@Bean
	public RouterFunction<ServerResponse> productServiceRoute() {

		// This configuration below mean:
		// Router Id: product_service
		// Every request url that match '/api/product' will be forwarded to
		// productServiceUrl
		// Note: You can use another function that RequestPredicates support to match
		// your request with the destination.
		return GatewayRouterFunctions.route("product_service")
				.route(RequestPredicates.path("/api/product"), HandlerFunctions.http(productServiceUrl)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> orderServiceRoute() {
		return GatewayRouterFunctions.route("order_service")
				.route(RequestPredicates.path("/api/order"), HandlerFunctions.http(orderServiceUrl)).build();
	}

	@Bean
	public RouterFunction<ServerResponse> inventoryServiceRoute() {
		return GatewayRouterFunctions.route("inventory_service")
				.route(RequestPredicates.path("/api/inventory"), HandlerFunctions.http(inventoryServiceUrl)).build();
	}
	
	// Aggregate routes for swagger-ui and api-docs

	@Bean
	public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
		return GatewayRouterFunctions.route("inventory_service_swagger")
				.route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"),
						HandlerFunctions.http(inventoryServiceUrl))
				.before(BeforeFilterFunctions.rewritePath("/aggregate/inventory-service/v3/api-docs", "/api-docs"))
				.build();
	}

	@Bean
	public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
		return GatewayRouterFunctions.route("order_service_swagger")
				.route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"),
						HandlerFunctions.http(orderServiceUrl))
				.before(BeforeFilterFunctions.rewritePath("/aggregate/order-service/v3/api-docs", "/api-docs")).build();
	}

	@Bean
	public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
		return GatewayRouterFunctions.route("product_service_swagger")
				.route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"),
						HandlerFunctions.http(productServiceUrl))
				.before(BeforeFilterFunctions.rewritePath("/aggregate/product-service/v3/api-docs", "/api-docs"))
				.build();
	}
	
	// End Aggregate routes for swagger-ui and api-docs
}
