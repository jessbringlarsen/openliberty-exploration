package io.openliberty.caching;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/cached")
public class CachedResource {

    @Inject
    private Service service;

    @GET
	public Response get() {
		return Response.ok(service.getARandomStringFrom("testing")).build();
	}
}