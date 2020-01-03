package com.example.Demostudent.validator;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;

import com.example.Demostudent.model.Student;

public class StudentValidator implements Validator<Student> {

	@Override
	public void validate(Student student) throws ValidationException {

		StringBuffer errorMessage = new StringBuffer();
		
	
		student.setErrorMessage(errorMessage.toString());
	}
}
