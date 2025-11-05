package com.th3angrycalf.mapper;

import com.th3angrycalf.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {


    /**
     * 查询所有部门数据
     */
    //方式三：camel映射
    //Mybatis会把字段名和属性名相同的数据自动封装，不相同的的用camel映射
    @Select("select id, name, create_time , update_time  from dept order by update_time desc ;")
    List<Dept> findAll();


    /**
     * 根据ID删除部门
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     *根据ID查询部门
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     *更新部门
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id = #{id}")
    void update(Dept dept);
}
