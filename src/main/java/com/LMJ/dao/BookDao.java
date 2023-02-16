package com.LMJ.dao;


import com.LMJ.pojo.Book;
import com.LMJ.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface BookDao {
    List<Book> selectBooksByCondition(@Param("book") Book book, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
    Integer countBooksByCondition(@Param("book") Book book);
    @Insert("insert into book(name,type,description) values(#{book.name},#{book.type},#{book.description})")
    Integer insertBook(@Param("book") Book book);
    @Delete("delete from book where id=#{id}")
    Integer deleteBookById(Integer id);
    @Update("update book set name=#{book.name},type=#{book.type},description=#{book.description} where id=#{book.id}")
    Integer updateBookById(@Param("book") Book book);
}
