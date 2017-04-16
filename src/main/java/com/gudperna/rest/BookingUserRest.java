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
import com.gudperna.dao.BookingUserDAO;
import com.gudperna.dao.impl.BookingUserDAOImpl;

import com.gudperna.model.BookingUser;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/bookingusers")
public class BookingUserRest {

	BookingUserDAO service = new BookingUserDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<BookingUser> dataList = service.getAll(); 

		GenericEntity<List<BookingUser>> list = new GenericEntity<List<BookingUser>>(dataList) { };
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

    @GET
    @Path("/booking/{booking_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByBooking(@PathParam("booking_id") int booking_id) {
    	List<BookingUser> dataList = service.getByBooking(booking_id);

    	GenericEntity<List<BookingUser>> list = new GenericEntity<List<BookingUser>>(dataList) { };
        return Response.ok(list)
            .build();
    }


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(BookingUser bookingUser) {
		service.insert(bookingUser);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(BookingUser bookingUser) {
		service.edit(bookingUser);

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