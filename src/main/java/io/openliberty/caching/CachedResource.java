package io.openliberty.caching;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	public Response getLatest() {
		return Response.ok(service.getARandomNumber()).build();
	}

    /**
     * 
     * @param date in the format yyyy-MM-dd e.g. '2011-12-03'
     * @return
     */
    @GET
    @Path("{date}")
	public Response get(@PathParam("date") String date) {
		return Response.ok(service.getARandomNumberFrom(LocalDate.parse(date, DateTimeFormatter.ISO_DATE))).build();
	}
}