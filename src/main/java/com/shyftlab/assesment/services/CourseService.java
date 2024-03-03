package com.shyftlab.assesment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyftlab.assesment.dtos.CourseDto;
import com.shyftlab.assesment.entities.Course;
import com.shyftlab.assesment.repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	public void saveCourse(String name) {
		Course course = new Course();
		course.setCourseName(name);
		courseRepository.save(course);

	}

	public List<CourseDto> getAllCourses() {
		List<Course> courses = (List<Course>) courseRepository.findAll();
		List<CourseDto> coursesDtoList = new ArrayList<>();

		for (Course course : courses) {
			coursesDtoList.add(convertToDto(course));
		}

		return coursesDtoList;
	}

	public CourseDto convertToDto(Course course) {
		CourseDto dto = new CourseDto();
		dto.setId(course.getId());
		dto.setName(course.getCourseName());
		return dto;
	}

	public void deleteCourse(Long id) {
		Course course = new Course();
		course.setId(id);
		courseRepository.delete(course);
	}

}
