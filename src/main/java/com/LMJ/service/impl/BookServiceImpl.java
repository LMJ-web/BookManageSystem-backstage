package com.LMJ.service.impl;

import com.LMJ.dao.BookDao;
import com.LMJ.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements com.LMJ.service.BookService {
    @Autowired
    private BookDao bookDao;
    @Override
    public List<Book> findBooksByCondition(Book book, Integer pageNum, Integer pageSize) {
        if(book.getName()!=null){
            book.setName("%"+book.getName()+"%");
        }
        if (book.getType()!=null){
            book.setType("%"+book.getType()+"%");
        }
        Integer beginNum=(pageNum-1)*pageSize;
        List<Book> books = bookDao.selectBooksByCondition(book, beginNum, pageSize);
        return books;
    }

    @Override
    public Integer calculateUsersByCondition(Book book) {
        Integer i = bookDao.countBooksByCondition(book);
        return i;
    }

    @Override
    public Boolean addBook(Book book) {
        Integer i = bookDao.insertBook(book);
        if (i>0){
            return true;
        }else return false;
    }

    @Override
    public Boolean removeBookById(Integer id) {
        Integer i = bookDao.deleteBookById(id);
        if (i>0){
            return true;
        }else return false;
    }

    @Override
    public Boolean editBook(Book book) {
        Integer i = bookDao.updateBookById(book);
        if (i>0){
            return true;
        }else return false;
    }
}
