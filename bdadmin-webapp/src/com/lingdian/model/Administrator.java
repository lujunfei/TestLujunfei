package com.lingdian.model;

import java.io.Serializable;

/**
 * Created by User on 2018/3/8. 后台管理账号类
 */
public class Administrator implements Serializable {

    private static final long serialVersionUID = 2990190987130427140L;

    private Long id;

    private String  userName;  //账号

    private String  password;  //密码

    private Integer status;   //账号是否有效 0无效 1有效

    private String  sessionKey;// 防止跨域请求

    private Integer  instId;   //公司id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }
}
