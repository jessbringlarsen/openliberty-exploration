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
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

public class EasyBatchApiTest {

    @Test
    public void test() {
        StringWriter output = new StringWriter();
        Job job = new JobBuilder()
                .reader(new StringRecordReader("testing"))
                .mapper(new EasyBatchApiResource.ToUpperStringMapper(String.class))
                .writer(new StringRecordWriter(output))
                .batchSize(10)
                .build();

        JobExecutor jobExecutor = new JobExecutor();
        JobReport report = jobExecutor.execute(job);
        jobExecutor.shutdown();

        Assert.assertEquals("TESTING", output.toString().trim());
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
