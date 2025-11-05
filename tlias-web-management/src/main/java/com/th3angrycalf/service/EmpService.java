package com.th3angrycalf.service;

import com.th3angrycalf.pojo.Emp;
import com.th3angrycalf.pojo.EmpQueryParam;
import com.th3angrycalf.pojo.LoginInfo;
import com.th3angrycalf.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页展示的记录数
     */
    //PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);


    /**
     * 分页查询
     * @param empQueryParam
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /**
     * 新增员工
     */
    void save(Emp emp);


    /**
     *批量删除员工
     */
    void deleteById(List<Integer> ids);

    /**
     *根据ID查询员工的详细信息
     */
    Emp getInfo(Integer id);

    /**
     *更新员工信息
     */
    void update(Emp emp);

    /**
     * 查询所有员工信息
     */
    List<Emp> list();

    /**
     * 根据部门ID查询员工信息
     */
    List<Emp> findByDeptId(Integer deptId);

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);
}
