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
import com.gudperna.dao.BookingDAO;
import com.gudperna.dao.impl.BookingDAOImpl;

import com.gudperna.model.Booking;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/bookings")
public class BookingRest {

	BookingDAO service = new BookingDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Booking> dataList = service.getAll(); 

		GenericEntity<List<Booking>> list = new GenericEntity<List<Booking>>(dataList) { };
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
	public Response add(Booking booking) {
		service.insert(booking);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Booking booking) {
		service.edit(booking);

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