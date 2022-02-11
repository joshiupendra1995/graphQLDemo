package com.uj.graphql.graphqldemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uj.graphql.graphqldemo.model.Book;
import com.uj.graphql.graphqldemo.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book get(DataFetchingEnvironment dataFetchingEnv) {
		String isn = dataFetchingEnv.getArgument("id");
		Optional<Book> optionalBook = bookRepository.findById(isn);
		if (optionalBook.isPresent()) {
			return optionalBook.get();
		}
		return null;
	}

}
