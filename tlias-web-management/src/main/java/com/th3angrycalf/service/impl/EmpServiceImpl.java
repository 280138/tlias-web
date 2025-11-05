package com.th3angrycalf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.th3angrycalf.mapper.EmpExprMapper;
import com.th3angrycalf.mapper.EmpMapper;
import com.th3angrycalf.pojo.*;
import com.th3angrycalf.service.EmpLogService;
import com.th3angrycalf.service.EmpService;
import com.th3angrycalf.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     *原始分页查询
     * @param page 页码
     * @param pageSize 每页展示的记录数
     * @return
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mapper接口，查询总记录数
        Long total = empMapper.count();
        //2.调用mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        //3.封装结果，PageResult
        return new PageResult<Emp>(total,rows);
    }*/

    /**
     * PageHelper分页查询
     * @param page 页码
     * @param pageSize 每页展示的记录数
     * 注意事项：
*             1.定义的SQL语句结尾不能加分号；
     *        2.PageHelper仅仅能对紧跟在其后的第一个查询语句进行分页处理
     */
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数（PageHelper)
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        //3.解析并封装结果
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(),p.getResult());*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数（PageHelper)
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析并封装结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    //事务管理 -- 默认运行时异常RuntimeException 才会回滚
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //int i = 1/0;

            //2.保存员工工作经历信息
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)){
                //便利集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(empId);
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3.记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"员工信息：" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(List<Integer> ids) {
        //根据id批量删除员工基本信息
        empMapper.delteByIds(ids);

        //2.根据员工的id批量删除员工的基本工作信息
        empExprMapper.deleteByEmpIds(ids);

    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {

        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.1 根据员工ID删除员工工作经历【删除旧的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 新增员工的工作经历数据【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        return empMapper.listAll();
    }

    @Override
    public List<Emp> findByDeptId(Integer deptId) {
        return empMapper.findByDeptId(deptId);
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if (empLogin != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",empLogin.getId());
            claims.put("username",empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(claims);//生成JWT令牌
            return new LoginInfo(empLogin.getId(),empLogin.getUsername(),empLogin.getName(),jwt);
        }
        return null;
    }

}
