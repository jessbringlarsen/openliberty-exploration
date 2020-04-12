package io.openliberty.batchapi;

import org.jeasy.batch.core.job.Job;
import org.jeasy.batch.core.job.JobBuilder;
import org.jeasy.batch.core.job.JobExecutor;
import org.jeasy.batch.core.job.JobReport;
import org.jeasy.batch.core.mapper.AbstractRecordMapper;
import org.jeasy.batch.core.mapper.RecordMapper;
import org.jeasy.batch.core.reader.StringRecordReader;
import org.jeasy.batch.core.record.Record;
import org.jeasy.batch.core.record.StringRecord;
import org.jeasy.batch.core.writer.StringRecordWriter;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.StringWriter;

@RequestScoped
@Path("/easybatch")
public class EasyBatchApiResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String startEasyBatchJob() {
        StringWriter output = new StringWriter();
        Job job = new JobBuilder()
                .reader(new StringRecordReader("testing"))
                .mapper(new ToUpperStringMapper(String.class))
                .writer(new StringRecordWriter(output))
                .batchSize(10)
                .build();

        JobExecutor jobExecutor = new JobExecutor();
        JobReport report = jobExecutor.execute(job);
        jobExecutor.shutdown();
        return output.toString();
    }

    public static class ToUpperStringMapper extends AbstractRecordMapper<String> implements RecordMapper<StringRecord, Record<String>> {
        /**
         * Create an {@link AbstractRecordMapper}.
         *
         * @param recordClass the target type
         */
        public ToUpperStringMapper(Class<String> recordClass) {
            super(recordClass);
        }

        @Override
        public Record<String> processRecord(StringRecord record) {
            return new StringRecord(record.getHeader(), record.getPayload().toUpperCase());
        }
    }
}