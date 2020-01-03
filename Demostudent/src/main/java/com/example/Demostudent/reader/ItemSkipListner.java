package com.example.Demostudent.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Demostudent.model.SkippedStudent;
import com.example.Demostudent.model.Student;
import com.example.Demostudent.writer.FailWriter;
import com.example.Demostudent.writer.SkipWriter;

@Component
public class ItemSkipListner implements SkipListener<Student, Student> {

	@Autowired
	SkipWriter skipWriter;

	@Autowired
	FailWriter failWriter;

	ExecutionContext executionContext;

	// method onSkipInrecords
	@Override
	public void onSkipInRead(Throwable t) {
		// printing flat file parse exception input
		System.out.println("in onSkipInRead --->" + ((FlatFileParseException) t).getInput());
		try {
			// skippedStudent class object
			SkippedStudent ss = new SkippedStudent();

			// set skipped_records is getting input from flat_file_parse_exception line
			ss.setSkippedRecord(((FlatFileParseException) t).getInput());

			// write skip_record as array_list
			skipWriter.write(Arrays.asList(ss));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// onSkipInWrite () method is taking Student class as parameter
	@Override
	public void onSkipInWrite(Student item, Throwable t) {
		System.out.println("in onSkipInWrite --->" + item);

	}

	@Override
	public void onSkipInProcess(Student item, Throwable t) {

		System.out.println("-------in Skip Process ----" + item);

		List<Student> list = new ArrayList<Student>();

		list.add(item);

		try {

			failWriter.write(list);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
