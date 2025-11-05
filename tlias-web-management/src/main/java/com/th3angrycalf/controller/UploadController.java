package com.th3angrycalf.controller;

import com.th3angrycalf.pojo.Result;
import com.th3angrycalf.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    /**
     * 本地磁盘方案
     */
/*    @PostMapping
    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
        log.info("文件上传：{}",file);
        //获取原始文件名
        String originalName = file.getOriginalFilename();
        //新的文件名
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extension;
        //保存文件
        file.transferTo(new File("D:/image/" + newFileName));
        return Result.success();
    }*/

    /**
     * 上传到阿里云
     */
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());

        //将文件交给OSS存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传OSS,url:{}",url);

        return Result.success(url);
    }

}
