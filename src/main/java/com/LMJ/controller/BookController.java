package com.LMJ.controller;
import com.LMJ.pojo.Book;
import com.LMJ.pojo.Code;
import com.LMJ.pojo.Result;
import com.LMJ.pojo.User;
import com.LMJ.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public Result findByCondition(Book book, Integer pageNum, Integer pageSize){
        Result result;
        List<Book> bookList = bookService.findBooksByCondition(book, pageNum, pageSize);
        Integer total = bookService.calculateUsersByCondition(book);
        result=new Result(bookList,total, Code.Query_OK,"查询成功！");
        return result;
    }
    @PostMapping
    public Result add(@RequestBody Book book){
        Result result;
        Boolean aBoolean = bookService.addBook(book);
        if (aBoolean){
            result = new Result(Code.ADD_OK,"添加成功！");
        }else {
            result = new Result(Code.ADD_ERR,"添加失败！");
        }
        return result;
    }
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Integer id){
        Result result;
        Boolean aBoolean = bookService.removeBookById(id);
        if (aBoolean){
            result = new Result(Code.REMOVE_OK,"删除成功！");
        }else {
            result = new Result(Code.REMOVE_ERR,"删除失败！");
        }
        return result;
    }
    @PutMapping
    public Result edit(@RequestBody Book book) {
        Result result;
        Boolean aBoolean = bookService.editBook(book);
        if (aBoolean){
            result = new Result(Code.CHANGE_OK,"修改成功！");
        }else {
            result = new Result(Code.CHANGE_ERR,"修改失败！");
        }
        return result;
    }
}
