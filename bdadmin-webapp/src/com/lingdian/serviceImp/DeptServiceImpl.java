package com.lingdian.serviceImp;

import com.lingdian.dao.DeptDao;
import com.lingdian.daoImp.DeptDaoImp;
import com.lingdian.model.Dept;
import com.lingdian.model.DeptQuery;
import com.lingdian.service.DeptService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public class DeptServiceImpl implements DeptService{
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b = false;
    //这里的 service层要想与 dao层(BAL层逻辑层)建立联系那么必须要 创建  dao层的对象
    DeptDao deptDao = new DeptDaoImp();//创建了 dao层的UserDaoImp对象
    @Override
    public Dept getDeptById(Long id) {
        return deptDao.getDeptById(id);
    }

    @Override
    public List<Dept> getDeptInfoAll(DeptQuery deptQuery) {
        return deptDao.getDeptInfoAll(deptQuery);
    }

    @Override
    public Dept insertDept(Dept dept) {
        return deptDao.insertDept(dept);
    }

    @Override
    public Integer updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public Integer updateQuitDept(Dept dept) {
        return deptDao.updateQuitDept(dept);
    }

    @Override
    public List<Dept> queryAllData(Long instId) {
        return null;
    }

    @Override
    public boolean insertData(Dept dept) {
        return false;
    }

    @Override
    public boolean update(Dept dept) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Dept queryDataById(int id) {
        return null;
    }
}
