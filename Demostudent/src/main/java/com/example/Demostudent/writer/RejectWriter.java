package com.example.Demostudent.writer;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import com.example.Demostudent.model.RejectStudent;


public class RejectWriter implements ItemWriter<RejectStudent> {


	
	ExecutionContext executionContext;

	@BeforeStep
	public void beforeSteps(StepExecution stepExecution) {
		executionContext = stepExecution.getExecutionContext();
	}

	@Override
	public void write(List<? extends RejectStudent> items) throws Exception {
		
		FlatFileItemWriter<RejectStudent> writer = new FlatFileItemWriter<RejectStudent>();
		writer.setResource(new FileSystemResource("C:\\Users\\sndani\\Downloads\\Demostudent\\src\\main\\resources\\Reject.csv"));
		writer.open(executionContext);

		DelimitedLineAggregator<RejectStudent> lineAggregator = new DelimitedLineAggregator<RejectStudent>();
		lineAggregator.setDelimiter(",");

		BeanWrapperFieldExtractor<RejectStudent> fieldExtractor = new BeanWrapperFieldExtractor<RejectStudent>();
		fieldExtractor.setNames(new String[] { "firstName", "middleName", "lastName", "college", "branch", "maths",
				"science", "hindi", "dbms", "english", "mathsMarks", "scienceMarks", "hindiMarks", "dbmsMarks",
				"englishMarks", "errorMessage" });

		lineAggregator.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(lineAggregator);

		System.out.println("========studentsREJECT========" + items.get(0));
		writer.write(items);
		writer.close();
		}

	}
	
		
	