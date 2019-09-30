package io.openliberty.dependencyinjection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/message")
public class MessageResource {

	@Inject
	private MessageProvider messageProvider;

	@GET
	public Response getMessage() {
		return Response.ok(messageProvider.message()).build();
	}
}
