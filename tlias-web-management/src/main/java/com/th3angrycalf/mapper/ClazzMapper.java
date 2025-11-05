package com.th3angrycalf.mapper;

import com.th3angrycalf.pojo.Clazz;
import com.th3angrycalf.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {
    /**
     * 条件查询员工信息
     */
    List<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级信息
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject,create_time,update_time)" +
            "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime});")
    void insert(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getInfoById(Integer id);

    /**
     * 查询所有班级信息
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> findAll();


    /**
     * 修改班级信息
     */
    void updateById(Clazz clazz);


    /**
     * 删除班级信息
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);




}
