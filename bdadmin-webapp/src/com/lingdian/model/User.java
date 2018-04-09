package com.lingdian.model;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by User on 2018/3/7.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 5569232459230006723L;

    private Long  id ;// 用户id

    private Long instId; //公司企业号

    private String  userName; //用户名

    private String  nickName;

    private String  headUrl;

    private String  jobNo;  //工号

    private String   phone; //手机号

    private Integer  userStatus ;

    private  Long    deptId;

    private  String   openId;

    private  Long    groupId;

    private Date createTime;

    private  Date  updateTime;

    private  Date lastSynTime;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public Date getLastSynTime() {
        return lastSynTime;
    }

    public void setLastSynTime(Date lastSynTime) {
        this.lastSynTime = lastSynTime;
    }
}
