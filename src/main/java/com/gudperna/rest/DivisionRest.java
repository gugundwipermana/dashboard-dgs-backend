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

@Path("/divisions")
public class DivisionRest {

	DivisionDAO service = new DivisionDAOImpl(ConnectionUtil.getConnection());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Division> getAll() {
		List<Division> listDivision = service.getAll(); 
		return listDivision; 
	}

}