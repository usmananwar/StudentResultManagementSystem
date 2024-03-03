package com.shyftlab.assesment.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	Student student;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	Course course;

	String score;

}
