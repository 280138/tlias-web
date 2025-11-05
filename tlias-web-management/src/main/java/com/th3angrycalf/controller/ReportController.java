package com.th3angrycalf.controller;

import com.th3angrycalf.pojo.ClazzOption;
import com.th3angrycalf.pojo.JobOption;
import com.th3angrycalf.pojo.Result;
import com.th3angrycalf.service.ReportService;
import com.th3angrycalf.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private StudentService studentService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别信息
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息：");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 统计班级人数
     */
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
        log.info("统计班级人数：");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }

    /**
     * 统计学员学历
     */
    @GetMapping("/studentDegreeData")
    public Result countStudentDegreeData(){
        log.info("统计学员学历数据");
        List<Map<String,Object>> list = reportService.countStudentDegreeData();
        return Result.success(list);
    }
}
