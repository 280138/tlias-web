package com.th3angrycalf.mapper;

import com.th3angrycalf.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 员工工作经历
 */
@Mapper
public interface EmpExprMapper {
    /**
     *批量插入员工经历
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     *根据员工的ID批量删除工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}
