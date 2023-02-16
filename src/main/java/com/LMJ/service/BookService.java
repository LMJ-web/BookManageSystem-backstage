package com.LMJ.service;

import com.LMJ.pojo.Book;
import com.LMJ.pojo.User;

import java.util.List;

public interface BookService {
    List<Book> findBooksByCondition(Book book,Integer pageNum,Integer pageSize);
    Integer calculateUsersByCondition(Book book);
    Boolean addBook(Book book);
    Boolean removeBookById(Integer id);
    Boolean editBook(Book book);
}
