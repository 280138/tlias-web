package com.th3angrycalf.controller;

import com.th3angrycalf.pojo.PageResult;
import com.th3angrycalf.pojo.Result;
import com.th3angrycalf.pojo.Student;
import com.th3angrycalf.pojo.StudentQueryParam;
import com.th3angrycalf.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询：{}",studentQueryParam);
        PageResult<Student> studentList = studentService.page(studentQueryParam);
        return Result.success(studentList);
    }

    /**
     * 添加学员
     */
    @PostMapping
    public Result add(@RequestBody Student student){
        log.info("添加学员：{}",student);
        studentService.add(student);
        return Result.success();
    }


    /**
     * 根据ID查询学员信息
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询学员信息：{}",id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生信息：{}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 批量删除学生
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除学生：{}",ids);
        studentService.deleteById(ids);
        return Result.success();
    }

    /**
     * 学生违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result handleDiscipline(@PathVariable Integer id,@PathVariable Short score){
        log.info("学生违纪处理：id:{},score:{}",id,score);
        studentService.handleDiscipline(id,score);
        return Result.success();
    }
}
