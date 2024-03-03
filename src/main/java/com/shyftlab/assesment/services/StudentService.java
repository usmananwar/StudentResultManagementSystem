package com.shyftlab.assesment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyftlab.assesment.dtos.StudentDto;
import com.shyftlab.assesment.entities.Student;
import com.shyftlab.assesment.repositories.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public void saveStudent(StudentDto studentDto) {
		Student student = convertToStudentEntity(studentDto);
		studentRepository.save(student);
	}

	public Student convertToStudentEntity(StudentDto studentDto) {

		Student student = new Student();

		student.setFirstName(studentDto.getFirstName());
		student.setFamilyName(studentDto.getFamilyName());
		student.setDob(studentDto.getDob());
		student.setEmail(studentDto.getEmail());

		return student;

	}

	public StudentDto convertToStudentDto(Student student) {

		StudentDto studentDto = new StudentDto();

		studentDto.setId(student.getId());
		studentDto.setFirstName(student.getFirstName());
		studentDto.setFamilyName(student.getFamilyName());
		studentDto.setDob(student.getDob());
		studentDto.setEmail(student.getEmail());

		return studentDto;

	}

	public List<StudentDto> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		List<StudentDto> studentDtoList = new ArrayList<>();

		for (Student student : students) {
			studentDtoList.add(convertToStudentDto(student));
		}

		return studentDtoList;
	}

	public void deleteStudent(Long id) {
		Student student = new Student();
		student.setId(id);
		studentRepository.delete(student);
	}

}
