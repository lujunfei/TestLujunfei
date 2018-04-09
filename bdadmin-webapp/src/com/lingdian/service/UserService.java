package com.lingdian.service;

import com.lingdain.command.UserServiceCommand;
import com.lingdian.model.User;
import com.lingdian.model.UserQuery;

import java.util.List;

/**
 * Created by User on 2018/3/7.
 */
public interface UserService extends UserServiceCommand{
    public List<User> queryAllData(Long insetId,int userStatus); //查询所有用户数据

    public List<User>  getUserInfoByConditions(UserQuery userQuery); //根据条件查询用户数据

    User  insertUser(User user);     //新增用户

    Integer  updateUser(User user);     //更新用户信息

    User  selectUserInfoById(Long  id);   //根据id查询用户信息

    Integer  quitUser(Long id);  //办理离职
}
