package com.example.Demostudent.reader;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import com.example.Demostudent.model.Student;

@Component
public class ItemReaderListner implements ItemReadListener<Student> {

	@Override
	public void beforeRead() {
		System.out.println("In Before Read");
		
	}

	@Override
	public void afterRead(Student item) {
		System.out.println("In after Read -> "+item);
		
	}

	@Override
	public void onReadError(Exception ex) {
		System.err.println("In onReadError "+ex);
		
	}

}
