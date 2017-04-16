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
import com.gudperna.dao.FileDAO;
import com.gudperna.dao.impl.FileDAOImpl;

import com.gudperna.model.File;

import javax.ws.rs.core.GenericEntity;

@Path("/secured/files")
public class FileRest {

	FileDAO service = new FileDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<File> dataList = service.getAll(); 

		GenericEntity<List<File>> list = new GenericEntity<List<File>>(dataList) { };
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
    @Path("/timelinedetails/{timelinedetail_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTimelineDetail(@PathParam("timelinedetail_id") int timelinedetail_id) {
    	List<File> dataList = service.getByTimelineDetail(timelinedetail_id);

    	GenericEntity<List<File>> list = new GenericEntity<List<File>>(dataList) { };
        return Response.ok(list)
            .build();
    }


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(File file) {
		service.insert(file);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(File file) {
		service.edit(file);

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