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
import com.gudperna.dao.TimelineDetailDAO;
import com.gudperna.dao.impl.TimelineDetailDAOImpl;

import com.gudperna.model.TimelineDetail;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/secured/timelinedetails")
public class TimelineDetailRest {

	TimelineDetailDAO service = new TimelineDetailDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	// public List<TimelineDetail> getAll() {
	public Response getAll() {
		List<TimelineDetail> listTimelineDetail = service.getAll(); 
		// return listTimelineDetail; 

		GenericEntity<List<TimelineDetail>> list = new GenericEntity<List<TimelineDetail>>(listTimelineDetail) { };
        return Response.ok(list)
            .build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public TimelineDetail getById(@PathParam("id") int id) {
	public Response getById(@PathParam("id") int id) {
		// return service.getById(id);

		return Response.ok(service.getById(id))
            .build();
	}


	@GET
	@Path("/timeline/{timeline_id}")
	@Produces(MediaType.APPLICATION_JSON)
	// public List<TimelineDetail> getByTimeline(@PathParam("timeline_id") int timeline_id) {
	public Response getByTimeline(@PathParam("timeline_id") int timeline_id) {
		List<TimelineDetail> listTimelineDetail = service.getByTimeline(timeline_id); 
		// return listTimelineDetail; 

		GenericEntity<List<TimelineDetail>> list = new GenericEntity<List<TimelineDetail>>(listTimelineDetail) { };
        return Response.ok(list)
            .build();
	}




	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(TimelineDetail timelineDetail) {
		service.insert(timelineDetail);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(TimelineDetail timelineDetail) {
		service.edit(timelineDetail);

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

// /src/main/java/com/gudperna/rest/TimelineDetailRest.java