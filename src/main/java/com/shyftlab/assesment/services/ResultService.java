package com.shyftlab.assesment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyftlab.assesment.dtos.CourseDto;
import com.shyftlab.assesment.dtos.ResultDto;
import com.shyftlab.assesment.dtos.StudentDto;
import com.shyftlab.assesment.entities.Course;
import com.shyftlab.assesment.entities.Result;
import com.shyftlab.assesment.entities.Student;
import com.shyftlab.assesment.repositories.ResultRepository;

@Service
public class ResultService {

	@Autowired
	ResultRepository resultRepository;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	public void saveResult(ResultDto resultDto) {

		Student student = new Student();
		student.setId(resultDto.getStudent().getId());
		Course course = new Course();
		course.setId(resultDto.getCourse().getId());

		Result result = new Result();
		result.setStudent(student);
		result.setCourse(course);
		result.setScore(resultDto.getScore());

		resultRepository.save(result);

	}

	public List<ResultDto> getAllResults() {
		List<Result> results = (List<Result>) resultRepository.findAllWithAssociations();
		List<ResultDto> resultDtoList = new ArrayList<>();

		for (Result result : results) {

			ResultDto dto = new ResultDto();

			StudentDto studentDto = studentService.convertToStudentDto(result.getStudent());
			dto.setStudent(studentDto);

			CourseDto courseDto = courseService.convertToDto(result.getCourse());
			dto.setCourse(courseDto);

			dto.setScore(result.getScore());

			resultDtoList.add(dto);
		}

		return resultDtoList;
	}

}
