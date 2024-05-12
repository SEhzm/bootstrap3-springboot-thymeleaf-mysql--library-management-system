package com.geo.homework2.repository;

import com.geo.homework2.pojo.Book;

import java.util.List;

public interface BookRepository {


    List<Book> findAllBook();

    int updateBook(Book book);

    Book findBookById(int id);

    int saveBook(Book book);

    int deleteBook(int id);

    List<Book> findBooksByCondtion(Book book);
}
