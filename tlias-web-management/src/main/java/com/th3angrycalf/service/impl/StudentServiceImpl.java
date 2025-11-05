package com.th3angrycalf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.th3angrycalf.mapper.StudentMapper;
import com.th3angrycalf.pojo.ClazzOption;
import com.th3angrycalf.pojo.PageResult;
import com.th3angrycalf.pojo.Student;
import com.th3angrycalf.pojo.StudentQueryParam;
import com.th3angrycalf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询
     */
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.PageHelper设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2.执行查询
        List<Student> studentList = studentMapper.page(studentQueryParam);
        //3.转化类型
        Page<Student> p = (Page<Student>)studentList;
        //4.封装结果
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }


    /**
     * 添加学员
     */
    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.add(student);
    }


    /**
     * 根据ID查询学生的详细信息
     */
    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 修改学生信息
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 批量删除学生数据
     */
    @Override
    public void deleteById(List<Integer> ids) {
        studentMapper.deleteById(ids);
    }


    /**
     * 学生违纪处理
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void handleDiscipline(Integer id, Short score) {
        //1.查询学生信息
        Student student = studentMapper.getById(id);
        if (student == null){
            throw new RuntimeException("学生不存在");
        }
        //2.添加违纪信息
        student.setViolationCount((short) (student.getViolationCount() + 1));
        student.setViolationScore((short) (student.getViolationScore() + score));
        student.setUpdateTime(LocalDateTime.now());
        //3.更新学生信息
        studentMapper.update(student);
    }


}
