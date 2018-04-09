package com.lingdian.model;

/**
 * Created by User on 2018/3/9.
 */
public class DeptQuery {
    private Long instId;

    private Long superDeptId;

    private String deptName;   //部门名称

    private Integer deptType; //是虚拟组还是真实组

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getSuperDeptId() {
        return superDeptId;
    }

    public void setSuperDeptId(Long superDeptId) {
        this.superDeptId = superDeptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }
}
