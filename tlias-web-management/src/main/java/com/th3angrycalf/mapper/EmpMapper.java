package com.th3angrycalf.mapper;

import com.th3angrycalf.pojo.Emp;
import com.th3angrycalf.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper {

    //-----------------------原始分页查询实现----------------------
    /**
     *查询总记录数
     */
    /*@Select("select count(*) from emp left join dept on emp.dept_id = dept.id;")
    Long count();*/

    /**
     *分页查询
     */
   /* @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
            "order by update_time desc limit #{start},#{pageSize}")
    List<Emp> list(Integer start,Integer pageSize);*/

    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by update_time desc")
    //List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息
     * @param empQueryParam
     */
    List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键 -- 主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "    values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     *批量删除员工信息
     */
    void delteByIds(List<Integer> ids);


    /**
     *根据ID查询员工详细信息
     */
    Emp getById(Integer id);

    /**
     * 更新员工基本信息
     */
    void updateById(Emp emp);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    /**
     * 查询全部员工
     */
    @Select("select * from emp")
    List<Emp> listAll();

    /**
     * 根据部门ID查询员工信息
     */
    @Select("select * from emp where dept_id = #{deptId}")
    List<Emp> findByDeptId(Integer deptId);

    /**
     * 员工登录
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
