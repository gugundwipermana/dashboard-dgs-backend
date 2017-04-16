package com.gudperna.rest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import java.security.Principal;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import javax.ws.rs.core.SecurityContext;

import org.glassfish.jersey.internal.util.Base64;


import com.gudperna.util.ConnectionUtil;
import com.gudperna.dao.UserDAO;
import com.gudperna.dao.impl.UserDAOImpl;
import com.gudperna.model.User;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization"; 
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic "; 
	
	private static final String SECURED_URL_PREFIX = "secured"; 

	String error_message = "User cannot access the resource.";

	UserDAO userService = new UserDAOImpl(ConnectionUtil.getConnection());

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (authHeader != null && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedString = Base64.decodeAsString(authToken);

				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":"); // melakukan split berdasarkan tanda ":" (email:password)

				// jika hasil decode token memiliki 2 value (username, password)
				if(tokenizer.countTokens() == 1) {
						error_message = "Invalid Token!";
				} else {

					final String email = tokenizer.nextToken();
					String password = tokenizer.nextToken();

					User user = new User();
					user.setEmail(email);
					user.setPassword(password);

					// optional
					final SecurityContext securityContext = requestContext.getSecurityContext();
					if(email != null) {
						requestContext.setSecurityContext(new SecurityContext() {
							@Override
	                        public Principal getUserPrincipal() {
	                            return new Principal() {
	                                @Override
	                                public String getName() {
	                                    return email;
	                                }
	                            };
	                        }

	                        @Override
	                        public boolean isUserInRole(String role) {
	                            return securityContext.isUserInRole(role);
	                        }

	                        @Override
	                        public boolean isSecure() {
	                            return securityContext.isSecure();
	                        }

	                        @Override
	                        public String getAuthenticationScheme() {
	                            return securityContext.getAuthenticationScheme();
	                        }
						});
					}
					
					if (userService.cekUser(user)) {
						return;
					}

				}
				
			}

			Response unauthorizedStatus = Response
								            .status(Response.Status.UNAUTHORIZED)
								            .entity(error_message)
								            .build();
					
			requestContext.abortWith(unauthorizedStatus);
			

		}
		
	}

}
