package com.uj.graphql.graphqldemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uj.graphql.graphqldemo.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

}
