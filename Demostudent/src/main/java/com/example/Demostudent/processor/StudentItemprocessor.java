package com.example.Demostudent.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.stereotype.Component;

import com.example.Demostudent.model.Student;

@Component
public class StudentItemprocessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student student) throws Exception {
		System.out.println("----------in processor-----------");

		float avg, perc, totalMarks = 0;

		totalMarks = (student.getMathsMarks() + student.getScienceMarks() + student.getHindiMarks()
				+ student.getDbmsMarks() + student.getEnglishMarks());

		// setNames(new String[] { "totalMarks" });

		avg = (totalMarks / 5);

		if (avg > 80) {
			System.out.println("----------in 80-----------");

			student.setGrade("A");
		} else if (avg > 60 && avg <= 80) {
			System.out.println("----------in 60-80-----------");

			student.setGrade("B");

		} else if (avg > 40 && avg <= 60) {
			student.setGrade("C");
		} else {
			student.setGrade("D");
		}

		perc = (totalMarks / 500) * 100;
		student.setPercantage(perc);
		student.setTotalMarks(totalMarks);
		System.out.println("student data -> " + student);

		if (student.getMathsMarks() <= 34 | student.getScienceMarks() <= 34 | student.getHindiMarks() <= 34
				| student.getDbmsMarks() <= 34 | student.getEnglishMarks() <= 34) {

			System.out.println("fail students");
			// throw new ValidationException("fail records");
			throw new FlatFileParseException("Cannot parse line to JSON", null);

			// return student;
		} else {

			System.out.println("student else------------");
			return student;

		}
	}

}
