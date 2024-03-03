package com.shyftlab.assesment.repositories;

import org.springframework.data.repository.CrudRepository;

import com.shyftlab.assesment.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}