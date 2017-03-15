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
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;

import com.gudperna.model.User;

import org.glassfish.jersey.internal.util.Base64;

import org.json.JSONObject;
import org.json.JSONException;


@Path("auth")
public class AuthRest {

	UserDAO userService = new UserDAOImpl(ConnectionUtil.getConnection());

	@POST
	@Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(User user) {

    	if(userService.cekUser(user)) {

    		String email = user.getEmail();
			String password = user.getPassword();

    		String token = Base64.encodeAsString(email+":"+password);

    		try {
    			return new JSONObject().put("status", "success").put("token", "Basic "+token).toString();
    		} catch (JSONException e) {
    			return null;
    		}

    	} else {
    		try {
    			return new JSONObject().put("status", "error").toString();
    		} catch (JSONException e) {
    			return null;
    		}
    	}
    }
	
}
