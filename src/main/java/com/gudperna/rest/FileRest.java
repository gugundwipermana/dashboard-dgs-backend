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

import com.gudperna.model.Filez;

import javax.ws.rs.core.GenericEntity;

// FOR UPLOAD -----------------------
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
// END ------------------------------

@Path("/secured/files")
public class FileRest {

	FileDAO service = new FileDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Filez> dataList = service.getAll(); 

		GenericEntity<List<Filez>> list = new GenericEntity<List<Filez>>(dataList) { };
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
    	List<Filez> dataList = service.getByTimelineDetail(timelinedetail_id);

    	GenericEntity<List<Filez>> list = new GenericEntity<List<Filez>>(dataList) { };
        return Response.ok(list)
            .build();
    }


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Filez file) {
		service.insert(file);

		return Response.status(201)
			.entity("Success")
			.build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Filez file) {
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



	// --------------------------
	@POST
	@Path("/upload")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadFile(
		@FormDataParam("file") InputStream fileInputStream,
		@FormDataParam("file") FormDataContentDisposition fileMetaData
	) throws Exception
	{
		String UPLOAD_PATH = "/Users/gugundwipermana/Sites/";
		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
			while ((read = fileInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw new WebApplicationException("Error");
		}

		return Response.ok("Success upload "+fileMetaData.getFileName()).build();
	}


}