package com.lingdian.serviceImp;

import com.lingdian.dao.UserDao;
import com.lingdian.daoImp.UserDaoImp;
import com.lingdian.model.User;
import com.lingdian.model.UserQuery;
import com.lingdian.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by User on 2018/3/7.业务逻辑
 */
public class UserServiceImpl implements UserService {
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b = false;
    //这里的 service层要想与 dao层(BAL层逻辑层)建立联系那么必须要 创建  dao层的对象
    UserDao myiTb_User = new UserDaoImp();//创建了 dao层的UserDaoImp对象

    @Override
    public List<User> queryAllData(Long instId,int  userStatus) {
        return myiTb_User.queryAllData(instId,userStatus);
    }
    @Override
    public List<User> getUserInfoByConditions(UserQuery userQuery) {
        return myiTb_User.getUserInfoByConfditions(userQuery);
    }

    @Override
    public User insertUser(User user) {
        return myiTb_User.insertUser(user);
    }

    @Override
    public Integer updateUser(User user) {
        return myiTb_User.updateUser(user);
    }

    @Override
    public User selectUserInfoById(Long id) {
        return  myiTb_User.selectUserInfoById(id);
    }

    @Override
    public Integer quitUser(Long id) {
        return myiTb_User.quitUser(id);
    }

    @Override
    public List queryAllData(Long instId) {
        return null;
    }

    @Override
    public boolean insertData(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Object queryDataById(int id) {
        return null;
    }
}
