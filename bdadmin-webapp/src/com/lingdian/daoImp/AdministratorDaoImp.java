package com.lingdian.daoImp;

import com.lingdian.dao.AdministratorDao;
import com.lingdian.model.Administrator;
import com.lingdian.model.User;
import com.lingdian.unit.UnitMysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public class AdministratorDaoImp  implements AdministratorDao{
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;

    @Override
    public List<Administrator> queryAllData(Long instId) {
        return null;
    }

    /**
     *登录验证
     */
    @Override
    public List<Administrator> getAdministratorInfoByConfditions(String userName, String password) {
        conn1 = UnitMysql.getConnection();// 链接数据库
        List<Administrator> list = new ArrayList<Administrator>();
        try {
            String sqlSelect = "select * from wxmnpro_run_administrator where user_name="+userName+" and password="+password+" and status="+1; // 查询多条数据
            ps = conn1.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            Administrator administrator = null;
            while (rs.next()) {
                administrator = new Administrator();
                administrator.setId(rs.getLong("id"));
                administrator.setSessionKey(rs.getString("session_key"));
                administrator.setInstId(rs.getInt("inst_id"));
                list.add(administrator);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return list;
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
