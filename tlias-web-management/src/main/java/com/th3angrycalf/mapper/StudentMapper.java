package com.th3angrycalf.mapper;

import com.th3angrycalf.pojo.Student;
import com.th3angrycalf.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 根据ID查询学生列表
     */
    @Select("select * from student where clazz_id = #{id}")
    List<Student> findById(Integer id);


    /**
     * 分页查询
     */
    List<Student> page(StudentQueryParam studentQueryParam);


    /**
     * 添加学员
     */
    void add(Student student);


    /**
     * 根据ID查询学生信息
     */
    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    Student getById(Integer id);


    /**
     * 修改学生信息
     */
    void update(Student student);


    /**
     * 批量删除学生数据
     */
    void deleteById(List<Integer> ids);

    /**
     * 统计班级学生人数
     */
    @MapKey("id")
    List<Map<String,Object>> getStudentCountData();

    /**
     * 统计学生学历
     */
    List<Map<String,Object>> countStudentDegreeData();
}
