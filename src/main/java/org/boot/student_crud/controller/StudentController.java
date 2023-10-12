package org.boot.student_crud.controller;

import java.util.List;

import org.boot.student_crud.dto.Student;
import org.boot.student_crud.helper.ResponseStructure;
import org.boot.student_crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentService service;

	// Save One Record
	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<Student>> save(@RequestBody Student student) {
		return new ResponseEntity<ResponseStructure<Student>>(service.save(student), HttpStatus.CREATED);
	}

	// Save Multiple Records
	@PostMapping("/students/many")
	public ResponseEntity<ResponseStructure<List<Student>>> save(@RequestBody List<Student> students) {
		return new ResponseEntity<ResponseStructure<List<Student>>>(service.save(students), HttpStatus.CREATED);
	}

	// FetchAll
	@GetMapping("/students")
	public ResponseEntity<ResponseStructure<List<Student>>> findAll() {
		return new ResponseEntity<ResponseStructure<List<Student>>>(service.findAll(), HttpStatus.FOUND);
	}

	// Fetch By Id
	@GetMapping("/students/{id}")
	public ResponseEntity<ResponseStructure<Student>> findById(@PathVariable int id) {
		return new ResponseEntity<ResponseStructure<Student>>(service.findById(id), HttpStatus.FOUND);
	}

	//Fetch By Mobile
	@GetMapping("/students/mobile/{mobile}")
	public ResponseEntity<ResponseStructure<Student>> findByMobile(@PathVariable long mobile) {
		return new ResponseEntity<ResponseStructure<Student>>(service.findByMobile(mobile), HttpStatus.FOUND);
	}

	//Fetch By Email
	@GetMapping("/students/email/{email}")
	public ResponseEntity<ResponseStructure<Student>> findByEmail(@PathVariable String email) {
		return new ResponseEntity<ResponseStructure<Student>>(service.findByEmail(email), HttpStatus.FOUND);
	}

	//Fetch by Percentage Greater than 
	@GetMapping("/students/percentage/greater/{percentage}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageGreater(@PathVariable double percentage) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(service.findByPercentageGreater(percentage),
                HttpStatus.FOUND);
    }

	//Fetch by Percentage Lesser than 
	@GetMapping("/students/percentage/lesser/{percentage}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByPercentageLesser(@PathVariable double percentage) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(service.findByPercentageLesser(percentage),
                HttpStatus.FOUND);
    }

	//Fetch by Result 
	@GetMapping("/students/result/{result}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByResult(@PathVariable String result) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(service.findByResult(result),
                HttpStatus.FOUND);
    }

	//Fetch by Maths and English 
	@GetMapping("/students/percentage/maths/english/greater/{marks}")
    public ResponseEntity<ResponseStructure<List<Student>>> findByMathsEnglishGreater(@PathVariable int marks) {
        return new ResponseEntity<ResponseStructure<List<Student>>>(service.findByMathsEnglishGreater(marks),
                HttpStatus.FOUND);
    }
}
