package com.th3angrycalf.service;

import com.th3angrycalf.pojo.ClazzOption;
import com.th3angrycalf.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {

    /**
     * 统计各个职位的员工人数
     */
    JobOption getEmpJobData();

    /**
     * 统计员工性别信息
     */
    List<Map<String, Object>> getEmpGenderData();


    /**
     * 统计班级人数
     */
    ClazzOption getStudentCountData();

    /**
     * 统计学员学历
     */
    List<Map<String, Object>> countStudentDegreeData();
}
