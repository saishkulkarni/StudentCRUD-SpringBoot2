package org.boot.student_crud.dao;

import java.util.List;

import org.boot.student_crud.dto.Student;
import org.boot.student_crud.exception.DataNotFoundException;
import org.boot.student_crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {

    @Autowired
    StudentRepository repository;

    public Student save(Student student) {
        return repository.save(student);
    }

    public List<Student> save(List<Student> students) {
        return repository.saveAll(students);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findById(int id) {
        return repository.findById(id).orElseThrow(()->{throw new DataNotFoundException("Data Not Found with Id "+id);});
    }

    public List<Student> findByName(String name) {
        return repository.findByName(name);
    }

    public Student findByMobile(long mobile) {
        return repository.findByMobile(mobile);
    }

    public Student findByEmail(String email) {
        return repository.findByEmail(email);
    }

	public List<Student> findPercentageGreater(double percentage) {
		return repository.findByPercentageGreaterThanEqual(percentage);
	}

    public List<Student> findPercentageLesser(double percentage) {
        return repository.findByPercentageLessThanEqual(percentage);
    }

    public List<Student> findByResult(String result) {
        return repository.findByResult(result);
    }

    public List<Student> findByMathsEnglishGreater(int marks) {
        return repository.findByMathsGreaterThanEqualAndEnglishGreaterThanEqual(marks,marks);
    }

}
