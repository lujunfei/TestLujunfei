package com.lingdian.Dto;

/**
 * Created by User on 2018/3/10.
 */
public class DeptDto {
    private  Long id;

    private  String  deptName;

    private  Integer number;//部门人数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
