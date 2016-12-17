package vn.com.arillance.honestfood.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements
		javax.ws.rs.ext.ExceptionMapper<Exception> {


	public Response toResponse(Exception e) {
		System.out.println(e.getMessage());
		return Response.status(500).entity(new ErrorMessage(e))
				.type(MediaType.APPLICATION_JSON).build();
	}
}
