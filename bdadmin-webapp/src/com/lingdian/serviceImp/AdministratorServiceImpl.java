package com.lingdian.serviceImp;

import com.lingdian.dao.AdministratorDao;
import com.lingdian.dao.DeptDao;
import com.lingdian.daoImp.AdministratorDaoImp;
import com.lingdian.daoImp.DeptDaoImp;
import com.lingdian.model.Administrator;
import com.lingdian.service.AdministratorService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public class AdministratorServiceImpl implements AdministratorService{
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b = false;
    //这里的 service层要想与 dao层(BAL层逻辑层)建立联系那么必须要 创建  dao层的对象
    AdministratorDao administratorDao = new AdministratorDaoImp();//创建了 dao层的UserDaoImp对象
    @Override
    public List<Administrator> getAdministratorInfoByConfditions(String userName, String password) {
        return administratorDao.getAdministratorInfoByConfditions(userName,password);
    }

    @Override
    public List<Administrator> queryAllData(Long instId) {
        return null;
    }

    @Override
    public boolean insertData(Administrator administrator) {
        return false;
    }

    @Override
    public boolean update(Administrator administrator) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Administrator queryDataById(int id) {
        return null;
    }
}
