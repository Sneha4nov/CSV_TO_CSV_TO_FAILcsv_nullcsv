package com.example.Demostudent.reader;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.example.Demostudent.model.Student;

public class Reader extends FlatFileItemReader<Student> {

	public Reader() {
		System.out.println("-----------------READER ---------------");
		setResource(new ClassPathResource("studentin.csv"));
		super.setLineMapper(new DefaultLineMapper<Student>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {

					{
						setNames(new String[] { "firstName", "middleName", "lastName", "college", "branch", "maths",
								"science", "hindi", "dbms", "english", "mathsMarks", "scienceMarks", "hindiMarks",
								"dbmsMarks", "englishMarks" });

					}
				});

				setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
					{
						setTargetType(Student.class);
					}
				});
			}
		});

	}

}
