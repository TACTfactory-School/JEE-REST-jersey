package com.tactfactory.poeioctober2019.controlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tactfactory.poeioctober2019.entities.Event;

@Path("/event")
public class EventControler extends CrudControllerBase<Integer, Event>{

    private static final String CONTEXT = "events";

    private final ServletContext servletContext;

    /**
     * Constructor of User controller.
     * @param servletContext Injected Servlet Context.
     */
    @SuppressWarnings("unchecked")
    public EventControler(@Context final ServletContext servletContext) {
        // Store injected ServletContext.
        this.servletContext = servletContext;

        // Check if Map of Users exist, if not create it.
        if (this.servletContext.getAttribute(CONTEXT) == null) {
            this.servletContext.setAttribute(CONTEXT, new HashMap<Integer, Event>());
        }

        // Store context of users.
        this.store = (Map<Integer, Event>) this.servletContext.getAttribute(CONTEXT);
    }


    /**
     * Retrieve the collection resource of User in the response body.
     * @return Collection of User.
     */
    @GET
    public Response getAll() {
        return super.getAll();
    }

    /**
     * Retrieve a User resource in the response body.
     * @param id Identifier of User.
     * @return User data.
     */
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") final Integer id) {
        return super.getOne(id);
    }

    /**
     * Create a User. The URI of the created member resource is automatically assigned and returned in the response
     * Location header field.
     * @param id Identifier of User.
     * @param user User data to store.
     * @return User data updated.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response create(@PathParam("id") final Integer id, final Event event) {
        return super.create(id, event);
    }

    /**
     * Create a collection of Users. The URI of the created member resource is automatically assigned and returned in the response
     * Location header field.
     * @param usersParam Collection of Users
     * @return Users data updated.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final List<Event> events) {
        return super.create(events);
    }

    /**
     * Update User or create it if not exist.
     * @param id Identifier of User.
     * @param user User data.
     * @return User data updated.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final Integer id, final Event event) {
        return super.update(id, event);
    }

    /**
     * Delete all the representations of User.
     * @param id Identifier of User.
     * @return nothing.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Integer id) {
        return super.delete(id);
    }
}
