package com.lingdian.daoImp;

import com.lingdian.dao.DeptDao;
import com.lingdian.model.Dept;
import com.lingdian.model.DeptQuery;
import com.lingdian.model.User;
import com.lingdian.unit.UnitMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/3/8.
 */
public class DeptDaoImp implements DeptDao {
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;

    @Override
    public Dept getDeptById(Long id) {
        conn1 = UnitMysql.getConnection();
        String sql = "select * from wxmnpro_run_deparment where id="+id;
        Dept dept = null;
        if (id > 0) {
            try {
                ps = conn1.prepareStatement(sql);
//                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    dept = new Dept();
                    dept.setId(rs.getLong("id"));
                    dept.setInstId(rs.getLong("inst_id"));
                    dept.setDeptName(rs.getString("dept_name"));
                    dept.setSuperDeptId(rs.getLong("super_dept_id"));
                    dept.setHeadUrl(rs.getString("head_url"));
                    dept.setDeptType(rs.getInt("dept_type"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                UnitMysql.Close(rs, ps, conn1);
            }
        }
        return dept;
    }

    @Override
    public List<Dept> getDeptInfoAll(DeptQuery query) {
        conn1 = UnitMysql.getConnection();// 连接数据库
        List<Dept> list = new ArrayList<Dept>();
        try {
            String sqlSelect = "select * from wxmnpro_run_deparment where  1=1  and  dept_type="+query.getDeptType();
             if(query.getDeptName() != null){
                sqlSelect += " and dept_name ='"+query.getDeptName()+"'";
            }
            if(query.getInstId() != null){
                sqlSelect += " and inst_id =" + query.getInstId();
            }

            ps = conn1.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            Dept dept = null;
            while (rs.next()) {
                dept = new Dept();
                dept.setId(rs.getLong("id"));
                dept.setInstId(rs.getLong("inst_id"));
                dept.setDeptName(rs.getString("dept_name"));
                dept.setDeptType(rs.getInt("dept_type"));
                list.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return list;
    }

    @Override
    public Dept insertDept(Dept dept) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        Dept depts = null;
        try {
            String sqlInsert = "insert into wxmnpro_run_deparment(inst_id,dept_name,head_url,create_time,update_time,dept_type) values(?,?,?,?,?,?);";
            ps = conn1.prepareStatement(sqlInsert,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            // 这里的1,2..必须要按上面的新增的顺序来定义
            ps.setLong(1, dept.getInstId());
            ps.setString(2, dept.getDeptName());
            ps.setString(3, dept.getHeadUrl());
//            ps.setLong(4, dept.getSuperDeptId());
            ps.setTimestamp(4,new Timestamp(dept.getCreateTime().getTime()));
            ps.setTimestamp(5,new Timestamp(dept.getUpdateTime().getTime()));
//            ps.setDate(5,new java.sql.Date(depts.getCreateTime().getTime()));
//            ps.setDate(6, new java.sql.Date(depts.getUpdateTime().getTime()));
            ps.setInt(6, dept.getDeptType());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys(); // 得到最新的ID
            if (rs.next()) {// 是否存在
                i = rs.getInt(1);
                Long sm = Long.valueOf(i);
                depts = getDeptById(sm);
                Dept dept1 = new Dept();
                dept1.setId(sm);
                dept1.setSuperDeptId(sm);
                updateDept(dept1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return depts;
    }

    @Override
    public Integer updateDept(Dept dept) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        try {
            String sqlUpdate = "update wxmnpro_run_deparment set ";
            if(dept.getDeptName() != null){
                sqlUpdate += "  dept_name ='"+dept.getDeptName()+"'";
            }
            if(dept.getSuperDeptId() != null && dept.getDeptName() != null){
                sqlUpdate += " ,super_dept_id ="+dept.getSuperDeptId() ;
            }else if(dept.getSuperDeptId() != null){
                sqlUpdate += " super_dept_id ="+dept.getSuperDeptId() ;
            }
            sqlUpdate +=" where id="+ dept.getId();
            ps = conn1.prepareStatement(sqlUpdate);
//            ps.setString(1, dept.getDeptName());
//            ps.setLong(2, dept.getId());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(null, ps, conn1);
        }
        return i;
    }

    @Override
    public Integer updateQuitDept(Dept dept) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        try {
            String sqlUpdate = "update wxmnpro_run_deparment set dept_type=? where id=?";
            ps = conn1.prepareStatement(sqlUpdate);
            ps.setInt(1, dept.getDeptType());
            ps.setLong(2, dept.getId());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(null, ps, conn1);
        }
        return i;
    }




    @Override
    public List<Dept> queryAllData(Long instId) {
        return null;
    }

    @Override
    public int insertData(Dept dept) {
        return 0;
    }

    @Override
    public int update(Dept dept) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public Dept queryDataById(int id) {
        return null;
    }
}
