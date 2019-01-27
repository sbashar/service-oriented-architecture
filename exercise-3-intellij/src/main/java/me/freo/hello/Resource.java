package me.freo.hello;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {
    @GET
    @Path("/")
    public Response sayHello() {
        return Response.status(201).entity("{ \"hello\": \"world\"} ").build();
    }
}
