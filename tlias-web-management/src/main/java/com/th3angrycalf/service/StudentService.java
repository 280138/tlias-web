package com.th3angrycalf.service;

import com.th3angrycalf.pojo.ClazzOption;
import com.th3angrycalf.pojo.PageResult;
import com.th3angrycalf.pojo.Student;
import com.th3angrycalf.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /**
     * 分页查询
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);


    /**
     *添加学员
     */
    void add(Student student);


    /**
     *根据ID查询学生信息
     */
    Student getById(Integer id);


    /**
     * 修学生信息
     */
    void update(Student student);


    /**
     * 批量删除学生数据
     */
    void deleteById(List<Integer> ids);


    /**
     * 学生违纪处理
     */
    void handleDiscipline(Integer id, Short score);



}
