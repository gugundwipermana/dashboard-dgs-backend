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

@Path("/users")
public class UserRest {

	UserDAO service = new UserDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAll() {
		List<User> listUser = service.getAll(); 
		return listUser; 
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getById(@PathParam("id") int id) {
		return service.getById(id);
	}

}

// /src/main/java/com/gudperna/rest/UserRest.java