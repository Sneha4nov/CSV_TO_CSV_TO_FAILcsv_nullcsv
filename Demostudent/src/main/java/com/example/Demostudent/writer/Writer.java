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

import com.example.Demostudent.model.Student;

public class Writer implements ItemWriter<Student> {

	ExecutionContext executionContext;

	@BeforeStep
	public void beforeSteps(StepExecution stepExecution) {
		executionContext = stepExecution.getExecutionContext();
	}

	@Override
	public void write(List<? extends Student> items) throws Exception {
		System.out.println("--------in write-------------");
		for(Student student:items)
		{
			System.out.println("--------in write student data------------"+student);

		}
		FlatFileItemWriter<Student> writer = new FlatFileItemWriter<Student>();
		writer.setResource(new FileSystemResource(
				"C:\\Users\\sndani\\Downloads\\Demostudent\\src\\main\\resources\\studentoutput.csv"));
		writer.open(executionContext);

		DelimitedLineAggregator<Student> lineAggregator = new DelimitedLineAggregator<Student>();
		lineAggregator.setDelimiter(",");

		BeanWrapperFieldExtractor<Student> fieldExtractor = new BeanWrapperFieldExtractor<Student>();
		fieldExtractor.setNames(new String[] { "firstName", "middleName", "lastName", "college", "branch", "maths",
				"science", "hindi", "dbms", "english", "mathsMarks", "scienceMarks", "hindiMarks", "dbmsMarks",
				"englishMarks", "totalMarks", "percantage", "grade" });

		lineAggregator.setFieldExtractor(fieldExtractor);
		writer.setLineAggregator(lineAggregator);

		System.out.println("========studentsin========" + items.get(0));
		writer.write(items);
		writer.close();

	}

}
