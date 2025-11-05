package com.th3angrycalf.exception;

import com.th3angrycalf.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice //代表定义了全局异常处理器
public class GlobalExceptionHandler {

    @ExceptionHandler //定义方法处理异常
    public Result handleException(Exception e){
        log.error("程序出错啦~",e);
        if(e.getMessage().contains("对不起，当前部门下有员工")){
            return Result.error(e.getMessage());
        }
        return Result.error("对不起，操作失败，请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错啦~",e);
        String msg = e.getMessage();
        int i = msg.indexOf("Duplicate entry");
        String errMsg = msg.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }

    @ExceptionHandler
    public Result handleClazzHasStudentException(ClazzHasStudentException e){
        log.error("程序出错啦~",e);
        return Result.error(e.getMessage());
    }

    /*@ExceptionHandler
    public Result handleDeptHasEmpException(DeptHasEmpException e){
        log.error("程序出错啦~",e);
        return Result.error(e.getMessage());
    }*/
}
