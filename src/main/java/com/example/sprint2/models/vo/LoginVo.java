package com.example.sprint2.models.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author hy
 * @Description 用户登录vo
 * @Date 2022/6/24
 */
@Data
public class LoginVo {

    @JsonProperty(value = "userid")
    //人事号或管理员账号
    private String userid;
    //姓名
    private String username;
    //密码
    private String password;
    private int identify;
    private String token;
    //性别
    private String gender;
    //职称
    private String title;
    //职级
    private String rank;
    //系
    private String faculty;


    public String getToken() {
        return this.token;
    }

    public String getUserId(){ return userid; }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int indentify) {
        this.identify = indentify;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public  void  setUserId(String userId){this.userid = userId;}


}
