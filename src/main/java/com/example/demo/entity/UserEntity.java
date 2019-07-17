package com.example.demo.entity;


import lombok.Data;

import java.sql.Timestamp;

/*
*用户实体类
* @Data注解在类上，会为类的所有属性自动生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。
*/
@Data
public class UserEntity {

    private int user_id;        //主键

    private String username;    //登陆账号名
    private String password;    //密码
    private String phone;       //电话
    private String sex;         //性别
    private String email;       //邮箱
    private Timestamp regtime;  //注册时间
    private Timestamp datetime; //最近登陆时间
    private int user_state;

}
