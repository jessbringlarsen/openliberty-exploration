package io.openliberty.caching;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/cached")
public class CachedResource {

    @Inject
    private Service service;

    @GET
    @Path("{name}")
	public Response get(@PathParam("name") String name) {
		return Response.ok(service.getARandomStringFrom(name)).build();
	}
}