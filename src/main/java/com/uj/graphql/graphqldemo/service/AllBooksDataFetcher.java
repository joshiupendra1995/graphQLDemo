package com.uj.graphql.graphqldemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uj.graphql.graphqldemo.model.Book;
import com.uj.graphql.graphqldemo.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> get(DataFetchingEnvironment arg0) {
		return bookRepository.findAll();
	}

}
