package com.th3angrycalf.service;


import com.th3angrycalf.pojo.Clazz;
import com.th3angrycalf.pojo.ClazzQueryParam;
import com.th3angrycalf.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 条件分页查询班级列表
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级信息
     */
    void save(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    Clazz getInfoById(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);


    /**
     * 根据ID删除班级信息
     */
    void deleteById(Integer id);


    /**
     * 查询所有班级信息
     */
    List<Clazz> findAll();
}
