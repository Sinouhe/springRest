package com.luv2code.springdemo.rest;


import com.luv2code.springdemo.entity.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	// @PostConstruct to load data
	@PostConstruct
	public void loadData() {
		this.theStudents = new ArrayList<>();
		this.theStudents.add(new Student("Poornima", "Patel"));
		this.theStudents.add(new Student("Mario", "Rossi"));
		this.theStudents.add(new Student("Mary", "Smith"));	
	}
	

	//define end point for /students - return list of students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if((studentId >= theStudents.size()) || (studentId < 0)) {
			throw new StudentNotFoundException("Student id not found " + studentId);
		}
		return theStudents.get(studentId);
	}
	
}
