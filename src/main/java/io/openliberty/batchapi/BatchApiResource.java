package io.openliberty.batchapi;

import javax.annotation.security.RolesAllowed;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Properties;

@RequestScoped
@Path("/batch")
public class BatchApiResource {

    @GET
    @Path("/")
    @RolesAllowed({"admin"})
    public JobExecution start() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        long executionId = jobOperator.start("job", new Properties());
        return jobOperator.getJobExecution(executionId);
    }
}