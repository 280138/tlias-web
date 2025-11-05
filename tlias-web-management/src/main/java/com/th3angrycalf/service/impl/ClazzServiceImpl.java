package com.th3angrycalf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.th3angrycalf.exception.ClazzHasStudentException;
import com.th3angrycalf.mapper.ClazzMapper;
import com.th3angrycalf.mapper.StudentMapper;
import com.th3angrycalf.pojo.Clazz;
import com.th3angrycalf.pojo.ClazzQueryParam;
import com.th3angrycalf.pojo.PageResult;
import com.th3angrycalf.pojo.Student;
import com.th3angrycalf.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数（PageHelper)
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> classList = clazzMapper.page(clazzQueryParam);
        classList.forEach(s -> setClazzStatus(s));
        //classList.forEach(s -> this.setClazzStatus(s));
        //classList.forEach(this::setClazzStatus);
        //3.解析并封装结果
        Page<Clazz> page = (Page<Clazz>)classList;
        return new PageResult<Clazz>(page.getTotal(),page.getResult());
    }

    /**
     * 新增班级信息
     */
    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID查询班级信息
     */
    @Override
    public Clazz getInfoById(Integer id) {
        return clazzMapper.getInfoById(id);
    }

    /**
     * 修改班级信息
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }


    /**
     * 根据ID删除班级信息
     */
    @Override
    public void deleteById(Integer id) {
        List<Student> students = studentMapper.findById(id);
        if (!students.isEmpty()){
            throw new ClazzHasStudentException("对不起，该班级下有学生，不能直接删除");
        }
        clazzMapper.deleteById(id);
    }


    /**
     * 查询所有班级信息
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    /**
     * 根据时间设置班级状态
     * @param clazz 班级对象
     */
    private void setClazzStatus(Clazz clazz) {
        LocalDate now = LocalDate.now();
        if (clazz.getEndDate() != null && now.isAfter(clazz.getEndDate())) {
            clazz.setStatus("已结课");
        } else if (clazz.getBeginDate() != null && now.isBefore(clazz.getBeginDate())) {
            clazz.setStatus("未开班");
        } else {
            clazz.setStatus("在读中");
        }
    }
}
