package com.lingdian.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by User on 2018/3/10.
 */
public class RunRecordHis {

    private Long id;

    private Long instId;

    private Long userId;

    private Long deptId;

    private BigDecimal runData;

    private Integer praiseNum;

    private Long groupId;

    private BigDecimal updateRunData;

    private Date createTime;

    private Date updateTime;

    private Date runTime;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public BigDecimal getRunData() {
        return runData;
    }

    public void setRunData(BigDecimal runData) {
        this.runData = runData;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public BigDecimal getUpdateRunData() {
        return updateRunData;
    }

    public void setUpdateRunData(BigDecimal updateRunData) {
        this.updateRunData = updateRunData;
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

    public Date getRunTime() {
        return runTime;
    }

    public void setRunTime(Date runTime) {
        this.runTime = runTime;
    }
}
