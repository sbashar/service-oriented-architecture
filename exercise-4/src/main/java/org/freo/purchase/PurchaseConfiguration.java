package org.freo.purchase;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/")
public class PurchaseConfiguration extends ResourceConfig {
	public PurchaseConfiguration() {
	}

	@PostConstruct
	public void setUp() {
		register(Purchase.class);
		register(OpenApiResource.class);
	}
}