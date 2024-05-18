package com.geo.homework2.service.impl;

import com.geo.homework2.pojo.Book;
import com.geo.homework2.repository.BookRepository;
import com.geo.homework2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBook(Model model) {
        return bookRepository.findAllBook();
    }

    @Override
    public int updateBook(Book book) {
        return bookRepository.updateBook(book);
    }

    @Override
    public Book findBookById(int id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public int saveBook(Book book) {
        return bookRepository.saveBook(book);
    }

    @Override
    public int deleteBook(String id) {
        return bookRepository.deleteBook(id);
    }

    @Override
    public List<Book> findBooksByCondtion(Book book) {
        return bookRepository.findBooksByCondtion(book);
    }
}
