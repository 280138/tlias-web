package com.th3angrycalf.service.impl;

import com.th3angrycalf.mapper.DeptMapper;
import com.th3angrycalf.pojo.Dept;
import com.th3angrycalf.pojo.Emp;
import com.th3angrycalf.service.DeptService;
import com.th3angrycalf.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpService empService;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        //1.根据部门ID查询员工信息
        List<Emp> empList = empService.findByDeptId(id);
        //2.判断员工信息是否为空
        if (!CollectionUtils.isEmpty(empList)){
            //3.不为空，则抛出异常
            throw new RuntimeException("对不起，当前部门下有员工，不能删除");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        //1.补全基础属性 - createTime,updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法插入数据
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1. 补全基础属性-updateTime
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法更新部门
        deptMapper.update(dept);
    }


}
