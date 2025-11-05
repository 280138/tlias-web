package com.th3angrycalf.service.impl;

import com.th3angrycalf.mapper.ClazzMapper;
import com.th3angrycalf.mapper.EmpMapper;
import com.th3angrycalf.mapper.StudentMapper;
import com.th3angrycalf.pojo.ClazzOption;
import com.th3angrycalf.pojo.JobOption;
import com.th3angrycalf.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClazzMapper clazzMapper;


    /**
     * 统计各个职位的员工人数
     */
    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData(); //map:  [   {"pos":"教研主管","num":1},   {"pos":"班主任","num":5},   {"pos":"讲师","num":10} ]

        //2.封装结果并返回
        //.map() 是映射/转换函数
        // 输入一个元素 → 经过函数处理 → 输出转换后的元素
        //Stream<Map<String, Object>> → map转换 → Stream<Object>
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList, dataList);
    }


    /**
     * 统计员工性别信息
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }


    /**
     * 统计班级人数
     */
    @Override
    public ClazzOption getStudentCountData() {
        List<Map<String,Object>> list = studentMapper.getStudentCountData();
        List clazzList = list.stream().map(dataMap ->  String.valueOf(dataMap.get("name"))).toList();
        List<Object> dataList =list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzOption(clazzList,dataList);
    }


    /**
     * 统计学员学历
     */
    @Override
    public List<Map<String, Object>> countStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }
}
