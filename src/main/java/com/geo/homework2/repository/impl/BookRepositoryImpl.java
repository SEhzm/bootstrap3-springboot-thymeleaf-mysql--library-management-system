package com.geo.homework2.repository.impl;

import com.geo.homework2.pojo.Book;
import com.geo.homework2.repository.BookRepository;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> findAllBook() {
        String sql = "select * from book ";
        //定义一个RowMapper
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update book set ID = ? ,name = ?, author = ? ,price = ?,publishtime = ? where id = ? ";
        Object args[] = {
                book.getID(),
                book.getName(),
                book.getAuthor(),
                book.getPrice(),
                book.getPublishtime(),
                book.getID(),
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public Book findBookById(int id) {
        String sql = "select * from book where id = ? ";
        Object args[] = {
                id
        };
        //定义一个RowMapper
        RowMapper<Book> rowMapper = new BeanPropertyRowMapper<Book>(Book.class);
        return jdbcTemplate.queryForObject(sql, args, rowMapper);
    }

    @Override
    public int saveBook(Book book) {
        String sql = "insert into book (ID,name, author, price, publishtime) values (?,?,?,?,?)";
        Object args[] = {
                book.getID(),
                book.getName(),
                book.getAuthor(),
                book.getPrice(),
                book.getPublishtime(),
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int deleteBook(int id) {
        String sql = "delete from book where id = ? ";
        Object args[] = {
                id
        };
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public List<Book> findBooksByCondtion(Book book) {
        StringBuilder sqlBuilder = new StringBuilder("select * from book where 1=1");
        List<Object> args = new ArrayList<>();

        if (StringUtils.isNotBlank(book.getID())) {
            sqlBuilder.append(" and id = ?");
            args.add(book.getID());
        }
        if (StringUtils.isNotBlank(book.getName())) { // 使用StringUtils来检查空字符串
            sqlBuilder.append(" and name = ?");
            args.add(book.getName());
        }
        if (StringUtils.isNotBlank(book.getAuthor())) {
            sqlBuilder.append(" and author = ?");
            args.add(book.getAuthor());
        }
        String sql = sqlBuilder.toString();
        log.info("查询书籍执行的sql：{}", sql);
        // 定义一个RowMapper
        RowMapper<Book> rowMapper = BeanPropertyRowMapper.newInstance(Book.class);
        return jdbcTemplate.query(sql, args.toArray(), rowMapper);
    }
}
