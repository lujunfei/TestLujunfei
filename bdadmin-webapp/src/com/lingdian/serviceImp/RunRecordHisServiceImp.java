package com.lingdian.serviceImp;

import com.lingdian.dao.DeptDao;
import com.lingdian.dao.RunRecordHisDao;
import com.lingdian.daoImp.DeptDaoImp;
import com.lingdian.daoImp.RunRecordHisDaoImp;
import com.lingdian.model.RunRecordHis;
import com.lingdian.model.RunRecordHisLog;
import com.lingdian.model.RunRecordHisQuery;
import com.lingdian.service.RunRecordHisService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by User on 2018/3/10.
 */
public class RunRecordHisServiceImp implements RunRecordHisService{
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b = false;
    //这里的 service层要想与 dao层(BAL层逻辑层)建立联系那么必须要 创建  dao层的对象
    RunRecordHisDao runRecordHisDao = new RunRecordHisDaoImp();//创建了 dao层的UserDaoImp对象

    @Override
    public List<RunRecordHis> selectRunRecorHisByCondition(RunRecordHisQuery runRecordHisQuery) {
        return runRecordHisDao.selectRunRecorHisByCondition(runRecordHisQuery);
    }

    @Override
    public RunRecordHisLog insertRecordHisLog(RunRecordHis runRecordHis) {
        return runRecordHisDao.insertRecordHisLog(runRecordHis);
    }

    @Override
    public Integer deleteHisById(Long id) {
        return runRecordHisDao.deleteHisById(id);
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
