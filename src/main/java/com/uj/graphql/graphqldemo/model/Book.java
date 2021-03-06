package com.uj.graphql.graphqldemo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

	@Id
	private String isn;
	private String title;
	private String publisher;
	private String [] authors;
	private String publisherDate;
	
}
