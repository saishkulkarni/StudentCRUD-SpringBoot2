package org.boot.student_crud.service;

import java.util.ArrayList;
import java.util.List;

import org.boot.student_crud.dao.StudentDao;
import org.boot.student_crud.dto.Student;
import org.boot.student_crud.exception.DataNotFoundException;
import org.boot.student_crud.exception.DataShouldNotRepeatException;
import org.boot.student_crud.helper.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	StudentDao dao;

	public ResponseStructure<Student> save(Student student) {

		Student student1 = dao.findByMobile(student.getMobile());
		Student student2 = dao.findByEmail(student.getEmail());

		if (student1 == null && student2 == null) {
			student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
			if (student.getMaths() < 35 || student.getEnglish() < 35 || student.getScience() < 35
					|| student.getPercentage() < 35)
				student.setResult("Fail");
			else {
				if (student.getPercentage() >= 85)
					student.setResult("Distinction");
				else if (student.getPercentage() >= 60)
					student.setResult("FirstClass");
				else
					student.setResult("SecondClass");
			}
			ResponseStructure<Student> structure = new ResponseStructure<>();
			structure.setData(dao.save(student));
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setMessage("Data Saved Success");
			return structure;
		} else {
			if (student1 == null)
				throw new DataShouldNotRepeatException("Email should Not be Repeated :" + student.getEmail());
			else if (student2 == null)
				throw new DataShouldNotRepeatException("Mobile should Not be Repeated :" + student.getMobile());
			else
				throw new DataShouldNotRepeatException(
						"Email and Mobile should Not be Repeated :" + student.getEmail() + "  " + student.getMobile());
		}
	}

	public ResponseStructure<List<Student>> save(List<Student> students) {
		List<Student> list = new ArrayList<>();
		for (Student student : students) {
			Student student1 = dao.findByMobile(student.getMobile());
			Student student2 = dao.findByEmail(student.getEmail());
			if (student1 == null && student2 == null)
				list.add(student);
		}
		if (list.isEmpty()) {
			throw new DataShouldNotRepeatException("All Data Already Exists");
		} else {
			for (Student student : list) {
				student.setPercentage((student.getMaths() + student.getEnglish() + student.getScience()) / 3.0);
				if (student.getMaths() < 35 || student.getEnglish() < 35 || student.getScience() < 35
						|| student.getPercentage() < 35)
					student.setResult("Fail");
				else {
					if (student.getPercentage() >= 85)
						student.setResult("Distinction");
					else if (student.getPercentage() >= 60)
						student.setResult("FirstClass");
					else
						student.setResult("SecondClass");
				}
			}
			ResponseStructure<List<Student>> structure = new ResponseStructure<>();

			if (list.size() != students.size())
				structure.setMessage(
						list.size() + " Data Saved, " + (students.size() - list.size()) + " Data Not Saved");
			else
				structure.setMessage("All Data Saved Success");

			structure.setData(dao.save(list));
			structure.setStatus(HttpStatus.CREATED.value());

			return structure;
		}
	}

	public ResponseStructure<List<Student>> findAll() {
		List<Student> list = dao.findAll();
		if (list.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			ResponseStructure<List<Student>> structure = new ResponseStructure<>();
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success");
			return structure;
		}
	}

	public ResponseStructure<Student> findById(int id) {
			ResponseStructure<Student> structure = new ResponseStructure<>();
			structure.setData(dao.findById(id));
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success");
			return structure;
	}

	public ResponseStructure<Student> findByMobile(long mobile) {
		Student student=dao.findByMobile(mobile);
		if(student==null)
		throw new DataNotFoundException("No Data Found with Mobile "+mobile);
		else{
			ResponseStructure<Student> structure = new ResponseStructure<>();
			structure.setData(student);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success");
			return structure;
		}
	}

    public ResponseStructure<Student> findByEmail(String email) {
        Student student=dao.findByEmail(email);
		if(student==null)
		throw new DataNotFoundException("No Data Found with Email "+email);
		else{
			ResponseStructure<Student> structure = new ResponseStructure<>();
			structure.setData(student);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success");
			return structure;
		}
    }

	public ResponseStructure<List<Student>> findByPercentageGreater(double percentage) {
		List<Student> list = dao.findPercentageGreater(percentage);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Percentage is Greater Than "+percentage);
		} else {
			ResponseStructure<List<Student>> structure = new ResponseStructure<>();
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success, "+list.size()+" Data is present");
			return structure;
		}
	}

	public ResponseStructure<List<Student>> findByPercentageLesser(double percentage) {
		List<Student> list = dao.findPercentageLesser(percentage);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Percentage is Lesser Than "+percentage);
		} else {
			ResponseStructure<List<Student>> structure = new ResponseStructure<>();
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success, "+list.size()+" Data is present");
			return structure;
		}
	}

    public ResponseStructure<List<Student>> findByResult(String result) {
        List<Student> list = dao.findByResult(result);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Result is "+result);
		} else {
			ResponseStructure<List<Student>> structure = new ResponseStructure<>();
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success, "+list.size()+" Data is present");
			return structure;
		}
    }

    public ResponseStructure<List<Student>> findByMathsEnglishGreater(int marks) {
        List<Student> list = dao.findByMathsEnglishGreater(marks);
		if (list.isEmpty()) {
			throw new DataNotFoundException("No Students Record, whose Marks in Maths and Science is Greater than "+marks);
		} else {
			ResponseStructure<List<Student>> structure = new ResponseStructure<>();
			structure.setData(list);
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setMessage("Data Found Success, "+list.size()+" Data is present");
			return structure;
		}
    }

	


}
