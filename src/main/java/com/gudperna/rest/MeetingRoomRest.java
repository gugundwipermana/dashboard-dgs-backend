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
import com.gudperna.dao.MeetingRoomDAO;
import com.gudperna.dao.impl.MeetingRoomDAOImpl;

import com.gudperna.model.MeetingRoom;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/meetingrooms")
public class MeetingRoomRest {

	MeetingRoomDAO service = new MeetingRoomDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<MeetingRoom> dataList = service.getAll(); 

		GenericEntity<List<MeetingRoom>> list = new GenericEntity<List<MeetingRoom>>(dataList) { };
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
	public Response add(MeetingRoom meetingRoom) {
		service.insert(meetingRoom);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(MeetingRoom meetingRoom) {
		service.edit(meetingRoom);

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