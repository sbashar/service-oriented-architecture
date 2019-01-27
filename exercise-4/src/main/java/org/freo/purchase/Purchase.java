package org.freo.purchase;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Component
@Path("/purchase")
@Produces(MediaType.APPLICATION_JSON)
@OpenAPIDefinition(info = @Info ( title = "PurchaseAPI", version = "0.0.2"))
public class Purchase {

	OrderRedis backend = new OrderRedis();

	// this is not right :-)
	// your job is to fix and annotate this so that it passes the Level1_POST test
	@GET
	@Path("{id}")
	public Response getOrder(@PathParam("id") String id) {
		 String orderJSON = null;
		 try {
		 	orderJSON = backend.getOrder(id);
		 	// returns a JSON string based on a UUID
		 } catch ( NotFoundException e) {
		 	return Response.status(404).entity(id).build();
		 }
		 return Response.status(200).entity(orderJSON).build();
	}

	@GET
	@Path("/")
	public Response getOrders() {
        String orderJSON = backend.getOrders();
		return Response.status(200).entity(orderJSON).build();
	}


	@PUT
	@Path("{id}")
	public Response updateOrder(@PathParam("id") String id, String input) {
		try {
			backend.updateOrder(id, input);
		} catch (JSONException je) {
			return Response.status(400).entity(input).build();

		} catch ( NotFoundException e) {
			return Response.status(404).entity(input).build();
		}
		return Response.status(200).entity(input).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteOrder(@PathParam("id") String id) {
		try {
            boolean deleted = backend.deleteOrder(id);
            if (deleted) {
                // successfully deleted
				return Response.status(200).entity(id).build();
            }
            else
            {
                // previously deleted
				return Response.status(410).entity(id).build();
            }
		} catch ( NotFoundException e) {
			return Response.status(404).entity(id).build();
		}
	}

	@POST
	@Path("/")
	public Response createOrder(String input, @Context UriInfo uriInfo) {

		String orderId = null;
		try {
			// this returns a simple UUID string
			orderId = backend.createOrder(input);
		} catch (JSONException je) {
			// else deal with this
			return Response.status(400).entity(input).build();
		}

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(orderId);
		return Response.created(builder.build()).entity(input).build();
	}

	// For level 2 and 3 you will need these examples

	// other backend examples
    // try {
	// 	backend.updateOrder(id, input);
	//  // this updates an order - takes a UUID and a JSON string
	// } catch (JSONException je) {
	// } catch (NotFoundException nfe) {
	// }

	// String orderJSON;
	// try {
	// 	orderJSON = backend.getOrder(id);
	// 	// returns a JSON string based on a UUID
	// } catch ( NotFoundException e) {
	// }

	// try {
	// 	boolean deleted = backend.deleteOrder(id);
	// 	if (deleted) {
	// 		// successfully deleted
	// 	}
	// 	else
	// 	{
	// 		// previously deleted
	// 	}
	// } catch ( NotFoundException e) {
	// 	// UUID has never existed
	// }

	// String allOrders = backend.getOrders();
	// // returns a JSON array containing hrefs to all the UUIDs in the database

}