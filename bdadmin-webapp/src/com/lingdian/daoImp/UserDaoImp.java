package com.lingdian.daoImp;

import com.lingdian.dao.UserDao;
import com.lingdian.model.Dept;
import com.lingdian.model.User;
import com.lingdian.model.UserQuery;
import com.lingdian.unit.UnitMysql;

import java.sql.*;
import java.util.List;

import java.util.ArrayList;

/**
 * Created by User on 2018/3/7.实现类
 */
public class UserDaoImp implements UserDao {
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;

    /**
     * 查询该企业下全部的人员信息
     *
     * @return
     */
    @Override
    public List<User> queryAllData(Long instId, int userStatus) {
        conn1 = UnitMysql.getConnection();// 链接数据库
        List<User> list = new ArrayList<User>();
        try {
            String sqlSelect = "select * from wxmnpro_run_user where inst_id=" + instId + " and  user_status=" + userStatus; // 查询多条数据
            ps = conn1.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setJobNo(rs.getString("job_no"));
                user.setInstId(rs.getLong("inst_id"));
                user.setUserName(rs.getString("user_name"));
                user.setDeptId(rs.getLong("dept_id"));
                user.setPhone(rs.getString("phone"));
                user.setUserStatus(rs.getInt("user_status"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return list;
    }

    @Override
    public List<User> getUserInfoByConfditions(UserQuery userQuery) {
        conn1 = UnitMysql.getConnection();// 连接数据库
        List<User> list = new ArrayList<User>();
        try {
            String sqlSelect = "select * from wxmnpro_run_user where  1=1 ";
            if (userQuery.getJobNo() != null) {
                sqlSelect += " and  job_no =" + userQuery.getJobNo();
            }
            if (userQuery.getUserName() != null) {
                sqlSelect += " and  user_name like '%" + userQuery.getUserName() + "%'";
            }
            if (userQuery.getPhone() != null) {
                sqlSelect += " and  phone like =" + userQuery.getPhone();
            }
            if (userQuery.getUserStatus() != null) {
                sqlSelect += " and user_status =" + userQuery.getUserStatus();
            }
            if(userQuery.getDeptId() != null){
                sqlSelect +=" and  dept_id ="+userQuery.getDeptId();
            }
            if(userQuery.getInstId() != null){
                sqlSelect +=" and  inst_id ="+userQuery.getInstId();
            }

            ps = conn1.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setInstId(rs.getLong("inst_id"));
                user.setJobNo(rs.getString("job_no"));
                user.setUserName(rs.getString("user_name"));
                user.setDeptId(rs.getLong("dept_id"));
                user.setPhone(rs.getString("phone"));
                user.setUserStatus(rs.getInt("user_status"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return list;
    }

    @Override
    public User insertUser(User user) {//新增用户
        conn1 = UnitMysql.getConnection();
        int i = 0;
        User users = null;
        try {
            String sqlInsert = "insert into wxmnpro_run_user(inst_id,user_name,nick_name,head_url,job_no,phone,user_status,dept_id,open_id," +
                    "group_id,create_time,update_time) values(?,?,?,?,?,?,?,?,?,?,?,?);";
            ps = conn1.prepareStatement(sqlInsert,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            // 这里的1,2..必须要按上面的新增的顺序来定义
            ps.setLong(1, user.getInstId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getNickName());
            ps.setString(4, user.getHeadUrl());
            ps.setString(5, user.getJobNo());
            ps.setString(6, user.getPhone());
            ps.setInt(7, user.getUserStatus());
            ps.setLong(8, user.getDeptId());
            ps.setString(9, user.getOpenId());
            ps.setLong(10, user.getGroupId());
            ps.setTimestamp(11,new Timestamp(user.getCreateTime().getTime()));
            ps.setTimestamp(12,new Timestamp(user.getUpdateTime().getTime()));
            ps.executeUpdate();
            rs = ps.getGeneratedKeys(); // 得到最新的ID
            if (rs.next()) {// 是否存在
                i = rs.getInt(1);
                Long sm = Long.valueOf(i);
                user = selectUserInfoById(sm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return user;
    }

    @Override
    public Integer updateUser(User user) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        try {
            String sqlUpdate = "update wxmnpro_run_user set user_name=?, job_no =? ,phone =?,dept_id=?,group_id=?  where id=?";
            ps = conn1.prepareStatement(sqlUpdate);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getJobNo());
            ps.setString(3, user.getPhone());
            ps.setLong(4, user.getDeptId());
            ps.setLong(5,user.getGroupId());
            ps.setLong(6, user.getId());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(null, ps, conn1);
        }
        return i;
    }

    @Override
    public User selectUserInfoById(Long id) {
        conn1 = UnitMysql.getConnection();
        String sql = "select * from wxmnpro_run_user where id=?";
        User user = null;
        if (id > 0) {
            try {
                ps = conn1.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setInstId(rs.getLong("inst_id"));
                    user.setJobNo(rs.getString("job_no"));
                    user.setUserName(rs.getString("user_name"));
                    user.setDeptId(rs.getLong("dept_id"));
                    user.setPhone(rs.getString("phone"));
                    user.setUserStatus(rs.getInt("user_status"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                UnitMysql.Close(null, ps, conn1);
            }
        }
        return user;
    }

    @Override
    public Integer quitUser(Long id) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        try {
            String sqlUpdate = "update wxmnpro_run_user set user_status=0 where id="+id;
            ps = conn1.prepareStatement(sqlUpdate);
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(null, ps, conn1);
        }
        return i;
    }


    @Override
    public List<User> queryAllData(Long instId) {
        return null;
    }

    @Override
    public int insertData(User userDomain) {
        return 0;
    }

    @Override
    public int update(User userDomain) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public User queryDataById(int id) {
        return null;
    }


}
