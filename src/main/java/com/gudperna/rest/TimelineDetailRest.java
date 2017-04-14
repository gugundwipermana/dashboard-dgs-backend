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

@Path("/timelinedetails")
public class TimelineDetailRest {

	TimelineDetailDAO service = new TimelineDetailDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TimelineDetail> getAll() {
		List<TimelineDetail> listTimelineDetail = service.getAll(); 
		return listTimelineDetail; 
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public TimelineDetail getById(@PathParam("id") int id) {
		return service.getById(id);
	}

}

// /src/main/java/com/gudperna/rest/TimelineDetailRest.java