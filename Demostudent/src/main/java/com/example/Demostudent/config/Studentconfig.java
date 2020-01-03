package com.example.Demostudent.config;

import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Demostudent.model.Student;
import com.example.Demostudent.processor.StudentItemprocessor;
import com.example.Demostudent.reader.ItemReaderListner;
import com.example.Demostudent.reader.ItemSkipListner;
import com.example.Demostudent.reader.Reader;
import com.example.Demostudent.validator.StudentValidator;
import com.example.Demostudent.writer.FailWriter;
import com.example.Demostudent.writer.RejectWriter;
import com.example.Demostudent.writer.SkipWriter;
import com.example.Demostudent.writer.Writer;

@Configuration
@EnableBatchProcessing
public class Studentconfig {
	
	private static final Logger logger = LoggerFactory.getLogger(Studentconfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	ValidatorFactory validatorFactory;

	@Autowired
	ItemReaderListner readListner;

	@Autowired
	ItemSkipListner itemSkipListner;

	@Bean
	@StepScope
	public Reader reader() throws Exception {
		return new Reader();
	}

	@Bean
	StudentValidator studentValidator() {
		return new StudentValidator();
	}

	@Bean
	RejectWriter rejectWriter() {
		return new RejectWriter();
	}

	@Bean
	SkipWriter skiptWriter() {
		return new SkipWriter();
	}

	@Bean
	FailWriter failWriter() {
		return new FailWriter();
	}

	@Bean
	@StepScope
	public StudentItemprocessor processor() {
		return new StudentItemprocessor();
	}

	@Bean
	@StepScope
	public Writer writer() {
		return new Writer();
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1").<Student, Student>chunk(10).reader(reader()).faultTolerant()
				.skip(FlatFileParseException.class).skipLimit(10).processor(processor()).writer(writer()).build();
	}

	@Bean
	public Step slaveStep() throws Exception {
		return stepBuilderFactory.get("slaveStep").<Student, Student>chunk(10).reader(reader()).listener(readListner)
				.processor(processor()).writer(writer()).faultTolerant().skip(FlatFileParseException.class)
				.skipLimit(10).listener(itemSkipListner).stream(skiptWriter()).stream(failWriter()).build();
	}

	@Bean
	public Job exportPersonJob() throws Exception {
		return jobBuilderFactory.get("exportStudentJob").incrementer(new RunIdIncrementer()).flow(slaveStep())
				.on(ExitStatus.COMPLETED.getExitCode()).to(step1()).end().build();

	}

}
