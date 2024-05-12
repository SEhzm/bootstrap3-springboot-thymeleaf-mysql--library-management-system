package com.geo.homework2.service;

import com.geo.homework2.pojo.Book;
import org.springframework.ui.Model;

import java.util.List;

public interface BookService {


    List<Book> findAllBook(Model model);

    int updateBook(Book book);

    Book findBookById(int id);

    int saveBook(Book book);

    int deleteBook(int id);

    List<Book> findBooksByCondtion(Book book);
}
