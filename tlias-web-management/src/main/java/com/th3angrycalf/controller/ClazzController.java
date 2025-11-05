package com.th3angrycalf.controller;

import com.th3angrycalf.pojo.Clazz;
import com.th3angrycalf.pojo.ClazzQueryParam;
import com.th3angrycalf.pojo.PageResult;
import com.th3angrycalf.pojo.Result;
import com.th3angrycalf.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("分页查询：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增班级信息
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级：{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 根据ID查询班级信息
     */
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id){
        log.info("查询班级信息：{}",id);
        Clazz clazz = clazzService.getInfoById(id);
        return Result.success(clazz);
    }


    /**
     * 修改班级信息
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 删除班级信息
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除班级：{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 查询所有班级信息
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查询所有班级信息：");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }
}
