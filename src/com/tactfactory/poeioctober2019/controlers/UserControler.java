package com.tactfactory.poeioctober2019.controlers;

import java.util.Collection;
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

import com.tactfactory.poeioctober2019.entities.User;

@Path("/user")
public class UserControler {

    private static final String CONTEXT = "users";

    private final ServletContext servletContext;

    private final Map<Integer, User> users;

    /**
     * Constructor of User controller.
     * @param servletContext Injected Servlet Context.
     */
    @SuppressWarnings("unchecked")
    public UserControler(@Context final ServletContext servletContext) {
        // Store injected ServletContext.
        this.servletContext = servletContext;

        // Check if Map of Users exist, if not create it.
        if (this.servletContext.getAttribute(CONTEXT) == null) {
            this.servletContext.setAttribute(CONTEXT, new HashMap<Integer, User>());
        }

        // Store context of users.
        this.users = (Map<Integer, User>) this.servletContext.getAttribute(CONTEXT);
    }

    /**
     * Retrieve the collection resource of User in the response body.
     * @return Collection of User.
     */
    @GET
    public Response getAll() {
        final Collection<User> result = users.values();

        return Response.ok(result).build();
    }

    /**
     * Retrieve a User resource in the response body.
     * @param id Identifier of User.
     * @return User data.
     */
    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") final Integer id) {
        Response result;
        final User user = users.get(id);

        if (user != null) {
            result = Response.ok(user).build();
        } else {
            result = Response.noContent().build();
        }

        return result;
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
    public Response create(@PathParam("id") final Integer id, final User user) {
        System.out.println("User" + user.displayName());

        if (user.id == 0) {
            user.id = ++User.sequence;
        }

        users.put(user.id, user);

        return Response.ok(user).build();
    }

    /**
     * Create a collection of Users. The URI of the created member resource is automatically assigned and returned in the response
     * Location header field.
     * @param usersParam Collection of Users
     * @return Users data updated.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(final List<User> usersParam) {

        for (User user : usersParam) {
            if (user.id == 0) {
                user.id = ++User.sequence;
            }

            users.put(user.id, user);
        }

        return Response.ok(usersParam).build();
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
    public Response update(@PathParam("id") final Integer id, final User user) {
        users.replace(id, user);

        return Response.ok(user).build();
    }

    /**
     * Delete all the representations of User.
     * @param id Identifier of User.
     * @return nothing.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") final Integer id) {
        users.remove(id);

        return Response.ok().build();
    }
}
