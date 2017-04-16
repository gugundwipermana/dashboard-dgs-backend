package com.gudperna.rest;

import java.util.List;

import javax.ws.rs.DELETE; 
import javax.ws.rs.GET; 
import javax.ws.rs.POST; 
import javax.ws.rs.PUT; 
import javax.ws.rs.Path; 
import javax.ws.rs.PathParam; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;

import com.gudperna.model.User;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/users")
public class UserRest {

	UserDAO service = new UserDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// public List<User> getAll() {
	public Response getAll() {
		List<User> listUser = service.getAll(); 

		// return listUser; 

		GenericEntity<List<User>> list = new GenericEntity<List<User>>(listUser) { };
        return Response.ok(list)
            .build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public User getById(@PathParam("id") int id) {
	public Response getById(@PathParam("id") int id) {
		// return service.getById(id);
		return Response.ok(service.getById(id))
            .build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(User user) {
		service.insert(user);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(User user) {
		service.edit(user);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		service.delete(id);

		return Response.status(201)
			.entity("Success")
			.build();
	}

}

// /src/main/java/com/gudperna/rest/UserRest.java