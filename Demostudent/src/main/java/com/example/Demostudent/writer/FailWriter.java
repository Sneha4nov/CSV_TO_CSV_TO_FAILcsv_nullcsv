package com.example.Demostudent.writer;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;

import com.example.Demostudent.model.Student;

public class FailWriter extends FlatFileItemWriter<Student> {

	ExecutionContext executionContext;

	@BeforeStep
	public void beforeSteps(StepExecution stepExecution) {
		this.executionContext = stepExecution.getExecutionContext();
	}

	public FailWriter() {
		super.setResource(
				new FileSystemResource("C:\\Users\\sndani\\Downloads\\Demostudent\\src\\main\\resources\\failSt.csv"));

		DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<Student>();
		lineAggregator.setDelimiter(",");

		BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<Student>();

		fieldExtractor.setNames(new String[] { "firstName", "middleName", "lastName", "college", "branch", "maths",
				"science", "hindi", "dbms", "english", "mathsMarks", "scienceMarks", "hindiMarks", "dbmsMarks",
				"englishMarks", "totalMarks", "percantage", "grade" });
		lineAggregator.setFieldExtractor(fieldExtractor);
		super.setLineAggregator(lineAggregator);

	}

	@AfterStep
	public void afterSteps(StepExecution stepExecution) {
		executionContext = stepExecution.getExecutionContext();
		super.close();
	}

}
