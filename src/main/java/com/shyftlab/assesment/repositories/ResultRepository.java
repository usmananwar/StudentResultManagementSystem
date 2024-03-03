package com.shyftlab.assesment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shyftlab.assesment.entities.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {

	@EntityGraph(attributePaths = { "student", "course" })
	@Query("SELECT r FROM Result r")
	List<Result> findAllWithAssociations();

}