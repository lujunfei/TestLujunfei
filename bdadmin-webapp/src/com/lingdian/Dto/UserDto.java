package com.lingdian.Dto;

import com.lingdian.model.User;

/**
 * Created by User on 2018/3/7.
 */
public class UserDto extends User {
    private   String  deptName;

    private   String  userStatusValue; //是否在职


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUserStatusValue() {
        return userStatusValue;
    }

    public void setUserStatusValue(String userStatusValue) {
        this.userStatusValue = userStatusValue;
    }
}
