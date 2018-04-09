package com.lingdian.dao;

import com.lingdain.command.UserServiceCommand;
import com.lingdian.model.RunRecordHis;
import com.lingdian.model.RunRecordHisLog;
import com.lingdian.model.RunRecordHisQuery;

import java.util.List;

/**
 * Created by User on 2018/3/10.
 */
public interface RunRecordHisDao extends UserServiceCommand<RunRecordHis>{

    List<RunRecordHis> selectRunRecorHisByCondition(RunRecordHisQuery runRecordHisQuery);

    RunRecordHisLog insertRecordHisLog(RunRecordHis runRecordHis);

    RunRecordHisLog selectHisLogInfoById(Long id);  //根据id查询

   Integer  deleteHisById(Long id);   //根据id删除
}
