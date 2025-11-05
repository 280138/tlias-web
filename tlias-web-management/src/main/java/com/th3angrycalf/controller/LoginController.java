package com.th3angrycalf.controller;

import com.th3angrycalf.pojo.Emp;
import com.th3angrycalf.pojo.LoginInfo;
import com.th3angrycalf.pojo.Result;
import com.th3angrycalf.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 员工登录
     */
    @PostMapping
    public Result login(@RequestBody Emp emp){
        log.info("登录: {}",emp);
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
