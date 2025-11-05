package com.th3angrycalf.service.impl;


import com.th3angrycalf.mapper.EmpLogMapper;
import com.th3angrycalf.pojo.EmpLog;
import com.th3angrycalf.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) //希望两个方法在独立的事务当中运行
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
