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
import com.gudperna.dao.DivisionDAO;
import com.gudperna.dao.impl.DivisionDAOImpl;

import com.gudperna.model.Division;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/divisions")
public class DivisionRest {

	DivisionDAO service = new DivisionDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// public List<Division> getAll() {
	public Response getAll() {
		List<Division> listDivision = service.getAll(); 
		// return listDivision; 

		GenericEntity<List<Division>> list = new GenericEntity<List<Division>>(listDivision) { };
        return Response.ok(list)
            .build();
	}

	@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) { 

        return Response.ok(service.getById(id))
            .build();
    }


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Division division) {
		service.insert(division);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Division division) {
		service.edit(division);

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