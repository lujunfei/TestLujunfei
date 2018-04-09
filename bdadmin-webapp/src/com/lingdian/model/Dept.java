package com.lingdian.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2018/3/8.部门相关字段信息
 */
public class Dept implements Serializable {

    private static final long serialVersionUID = 7897725324495957133L;

    private  Long  id;

    private  Long  instId;

    private String deptName;

    private Long  superDeptId;

    private String headUrl;

    private Date   createTime;

    private Date   updateTime;

    private Integer deptType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getSuperDeptId() {
        return superDeptId;
    }

    public void setSuperDeptId(Long superDeptId) {
        this.superDeptId = superDeptId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }
}
