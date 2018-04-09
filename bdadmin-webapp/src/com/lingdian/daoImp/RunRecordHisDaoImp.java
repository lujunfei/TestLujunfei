package com.lingdian.daoImp;

import com.lingdian.dao.RunRecordHisDao;
import com.lingdian.model.*;
import com.lingdian.unit.UnitMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2018/3/10.
 */
public class RunRecordHisDaoImp implements RunRecordHisDao {
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;

    @Override
    public List<RunRecordHis> selectRunRecorHisByCondition(RunRecordHisQuery query) {
        conn1 = UnitMysql.getConnection();// 连接数据库
        List<RunRecordHis> list = new ArrayList<RunRecordHis>();
        try {
//            String sqlSelect = "select * from wxmnpro_run_record_his where  1=1 and  inst_id = "+query.getInstId()+" and user_id="+
//                    query.getUserId()+" and dept_id ="+query.getDeptId();
            String sqlSelect = "select * from wxmnpro_run_record_his where  1=1";
            if (query.getInstId() != null) {
                sqlSelect += " and inst_id =" + query.getInstId();
            }
            if (query.getUserId() != null) {
                sqlSelect += " and user_id =" + query.getUserId();
            }
            if(query.getDeptId() != null){
                sqlSelect += " and dept_id =" + query.getDeptId();
            }
            ps = conn1.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            RunRecordHis runRecordHis = null;
            while (rs.next()) {
                runRecordHis = new RunRecordHis();
                runRecordHis.setId(rs.getLong("id"));
                runRecordHis.setInstId(rs.getLong("inst_id"));
                runRecordHis.setUserId(rs.getLong("user_id"));
                runRecordHis.setDeptId(rs.getLong("dept_id"));
                runRecordHis.setRunData(rs.getBigDecimal("run_data"));
                runRecordHis.setPraiseNum(rs.getInt("praise_num"));
                runRecordHis.setGroupId(rs.getLong("group_id"));
                runRecordHis.setUpdateRunData(rs.getBigDecimal("update_run_data"));
                runRecordHis.setCreateTime(rs.getTimestamp("create_time"));
                runRecordHis.setUpdateTime(rs.getTimestamp("update_time"));
                runRecordHis.setRunTime(rs.getTimestamp("run_time"));
                list.add(runRecordHis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return list;
    }

    @Override
    public RunRecordHisLog insertRecordHisLog(RunRecordHis runRecordHis) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        RunRecordHisLog runRecordHisLog = null;
        try {
            String sqlInsert = "insert into wxmnpro_run_record_his_log(id,inst_id,user_id,dept_id,run_data,praise_num,group_id,update_run_data,create_time,update_time,run_time) values(?,?,?,?,?,?,?,?,?,?,?);";
            ps = conn1.prepareStatement(sqlInsert,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            // 这里的1,2..必须要按上面的新增的顺序来定义
            ps.setLong(1, runRecordHis.getId());
            ps.setLong(2, runRecordHis.getInstId());
            ps.setLong(3, runRecordHis.getUserId());
            ps.setLong(4, runRecordHis.getDeptId());
            ps.setBigDecimal(5, runRecordHis.getRunData());
            ps.setInt(6, runRecordHis.getPraiseNum());
            ps.setLong(7, runRecordHis.getGroupId());
            ps.setBigDecimal(8, runRecordHis.getUpdateRunData());
            ps.setTimestamp(9,new Timestamp(runRecordHis.getCreateTime().getTime()));
            ps.setTimestamp(10,new Timestamp(runRecordHis.getUpdateTime().getTime()));
            ps.setTimestamp(11,new Timestamp(runRecordHis.getRunTime().getTime()));
//            ps.setDate(9, new java.sql.Date(runRecordHis.getCreateTime().getTime()));
//            ps.setDate(10, new java.sql.Date(runRecordHis.getUpdateTime().getTime()));
//            ps.setDate(11, new java.sql.Date(runRecordHis.getRunTime().getTime()));
            ps.executeUpdate();
            rs = ps.getGeneratedKeys(); // 得到最新的ID
            if (rs.next()) {// 是否存在
                i = rs.getInt(1);
                Long sm = Long.valueOf(i);
                runRecordHisLog = selectHisLogInfoById(sm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(rs, ps, conn1);
        }
        return runRecordHisLog;
    }

    @Override
    public RunRecordHisLog selectHisLogInfoById(Long id) {
        conn1 = UnitMysql.getConnection();
        String sql = "select * from wxmnpro_run_record_his_log where id=?";
        RunRecordHisLog runRecordHisLog = null;
        if (id > 0) {
            try {
                ps = conn1.prepareStatement(sql);
                ps.setLong(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    runRecordHisLog = new RunRecordHisLog();
                    runRecordHisLog.setId(rs.getLong("id"));
                    runRecordHisLog.setInstId(rs.getLong("inst_id"));
                    runRecordHisLog.setDeptId(rs.getLong("dept_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                UnitMysql.Close(null, ps, conn1);
            }
        }
        return runRecordHisLog;
    }

    @Override
    public Integer deleteHisById(Long id) {
        conn1 = UnitMysql.getConnection();
        int i = 0;
        try {
            String sqlDelete = "delete from wxmnpro_run_record_his where id=?";
            ps = conn1.prepareStatement(sqlDelete);
            ps.setLong(1, id);
            i = ps.executeUpdate();
            if (i == 1) {
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            UnitMysql.Close(null, ps, conn1);
        }
        return i;
    }


    @Override
    public List<RunRecordHis> queryAllData(Long instId) {
        return null;
    }

    @Override
    public boolean insertData(RunRecordHis runRecordHis) {
        return false;
    }

    @Override
    public boolean update(RunRecordHis runRecordHis) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public RunRecordHis queryDataById(int id) {
        return null;
    }


}
