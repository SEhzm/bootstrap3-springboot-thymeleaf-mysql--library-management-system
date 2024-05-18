package com.geo.homework2.controller;


import com.geo.homework2.pojo.Book;
import com.geo.homework2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/showAllBook")
    public String findAllBook(Model model) {
        model.addAttribute("bookcond", new Book());
        model.addAttribute("title", "所有的书籍");
        List<Book> booklist = bookService.findAllBook(model);
        model.addAttribute("booklist", booklist);
        return "showAllBook";
    }


    @RequestMapping("/updatebook")
    public String updateBook(@RequestParam("ID") String id,
                             @RequestParam("name") String name,
                             @RequestParam("author") String author,
                             @RequestParam("price") Double price,
                             @RequestParam("publishtime") String publishTime,
                             Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(publishTime);
        Book book = new Book(id, name, author, price, date);
        int res = bookService.updateBook(book);
        if (res > 0) {
            findAllBook(model);
            return "showAllBook";
        }
        model.addAttribute("msg", "书籍信息更新不成功！");
        return "error";
    }

    @RequestMapping("/findbook")
    public String findBookById(int id, Model model) {
        Book book = bookService.findBookById(id);
        model.addAttribute("updatebook", book);
        model.addAttribute("title", "修改书籍信息");
        return "showABook";
    }

    @RequestMapping("/addbookpath")
    public String addBookPage(@ModelAttribute("addbook") Book book, Model model) {
        model.addAttribute("title", "添加书籍");
        return "addBook";
    }

    @GetMapping("/addbook")
    public String addBook(@RequestParam("ID") String id,
                          @RequestParam("name") String name,
                          @RequestParam("author") String author,
                          @RequestParam("price") Double price,
                          @RequestParam("publishtime") String publishTime,
                          Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(publishTime);
        Book book = new Book(id, name, author, price, date);
        int res = bookService.saveBook(book);
        if (res > 0) {
            findAllBook(model);
            return "showAllBook";
        }
        model.addAttribute("msg", "添加不成功！已存在书号");
        return "error";
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(String id, Model model) {
        int res = bookService.deleteBook(id);
        if (res > 0) {
            findAllBook(model);
            return "showAllBook";
        }
        model.addAttribute("msg", "删除不成功！");
        return "error";
    }

    @RequestMapping("/queryBook")
    public String queryBooksByCondition(Book book, Model model) {
        List<Book> books = bookService.findBooksByCondtion(book);
        if (books != null) {
            model.addAttribute("booklist", books);
            model.addAttribute("title", "查询结果");
            model.addAttribute("bookcond", book);
            return "showAllBook";
        } else {
            model.addAttribute("msg", "未查到书籍！");
        }
        return "error";
    }

}
