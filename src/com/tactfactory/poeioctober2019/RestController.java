package com.tactfactory.poeioctober2019;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/test")
public class RestController {

    @GET
    public Response sayHello() {
        return  Response.ok("Hello").build();
    }

    @GET
    @Path("/world")
    public Response sayWorld() {
        return  Response.ok("Hello World").build();
    }
}
