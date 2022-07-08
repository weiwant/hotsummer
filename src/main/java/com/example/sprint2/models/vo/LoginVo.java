package com.example.sprint2.models.vo;

/**
 * @author hy
 * @Description 用户登录vo
 * @Date 2022/6/24
 */
public class LoginVo {
    private String username;
    private String password;
    private int indentify;
    private String token;


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public int getIdentify() {
        return indentify;
    }

    public void setIdentify(int indentify) {
        this.indentify = indentify;
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

    public String getToken() {
        return this.token;
    }
}
