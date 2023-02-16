package com.LMJ.controller;

import com.LMJ.pojo.Code;
import com.LMJ.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        return new Result(Code.UNKNOWN_ERR,"系统繁忙，请稍后再试！");
    }
}
