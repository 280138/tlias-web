package com.th3angrycalf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzOption {
    private List clazzList;  //班级列表
    private List dataList;   //数据列表
}
