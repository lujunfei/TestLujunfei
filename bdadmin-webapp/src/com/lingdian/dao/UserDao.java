package com.lingdian.dao;

import com.lingdain.command.UserDaoCommand;
import com.lingdian.model.User;
import com.lingdian.model.UserQuery;

import java.util.List;

/**
 * Created by User on 2018/3/7.
 */
public interface UserDao extends UserDaoCommand<User> {

    List<User> queryAllData(Long instId,int userStatus);

    List<User> getUserInfoByConfditions(UserQuery userQuery);

    User insertUser(User user);

    Integer  updateUser(User user);

    User  selectUserInfoById(Long id);

    Integer  quitUser(Long id);


}
