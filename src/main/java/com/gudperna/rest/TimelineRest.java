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

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.TimelineDAO;
import com.gudperna.dao.impl.TimelineDAOImpl;

import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;

import com.gudperna.model.Timeline;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/timelines")
public class TimelineRest {

	TimelineDAO service = new TimelineDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// public List<Timeline> getAll() {
	public Response getAll() {
		List<Timeline> listTimeline = service.getAll(); 
		// return listTimeline; 

		GenericEntity<List<Timeline>> list = new GenericEntity<List<Timeline>>(listTimeline) { };
        return Response.ok(list)
            .build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public Timeline getById(@PathParam("id") int id) {
	public Response getById(@PathParam("id") int id) {
		// return service.getById(id);
		return Response.ok(service.getById(id))
            .build();
	}

	@GET
	@Path("/by/{by}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBy(@PathParam("by") String by, @Context SecurityContext ctx) {

		UserDAO serviceUser = new UserDAOImpl(ConnectionUtil.getConnection());

		int id = 0;

		// if(by == "c") {
		// 	id = serviceUser.getFilterIdByEmail(by, ctx.getUserPrincipal().getName());
		// } else if(by == "d") {
		// 	id = serviceUser.getFilterIdByEmail(by, ctx.getUserPrincipal().getName());
		// } else if(by == "e") {
		// 	id = serviceUser.getFilterIdByEmail(by, ctx.getUserPrincipal().getName());
		// }

		id = serviceUser.getFilterIdByEmail(by, ctx.getUserPrincipal().getName());

		List<Timeline> listTimeline = service.getBy(by, id);

		GenericEntity<List<Timeline>> list = new GenericEntity<List<Timeline>>(listTimeline) { };
        return Response.ok(list)
            .build();
	}



	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Timeline timeline) {
		service.insert(timeline);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Timeline timeline) {
		service.edit(timeline);

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

// /src/main/java/com/gudperna/rest/TimelineRest.java