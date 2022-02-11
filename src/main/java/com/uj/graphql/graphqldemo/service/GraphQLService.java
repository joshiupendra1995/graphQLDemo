package com.uj.graphql.graphqldemo.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.uj.graphql.graphqldemo.model.Book;
import com.uj.graphql.graphqldemo.repository.BookRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	@Value("classpath:books.graphql")
	Resource resource;
	private GraphQL graphQL;
	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;

	@Autowired
	private BookRepository bookRepository;

	@PostConstruct
	private void loadSchema() throws IOException {
		loadDataIntoHSQL();

		File schemaFile = resource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private void loadDataIntoHSQL() {
		Stream.of(new Book("123", "Book of Clouds", "Kindle", new String[] { "Chloe" }, "Nov 2017"),
				new Book("124", "Cloud Architecture", "Kindle", new String[] { "Ajay" }, "Dec 2017"),
				new Book("125", "Secrets of Books", "Kindle", new String[] { "Saram" }, "Jan 2020")).forEach(book -> {
					bookRepository.save(book);
				});

	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher).dataFetcher("book", bookDataFetcher)).build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}
}
