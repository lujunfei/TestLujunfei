package com.lingdian.service;

import com.lingdain.command.UserServiceCommand;
import com.lingdian.model.RunRecordHis;
import com.lingdian.model.RunRecordHisLog;
import com.lingdian.model.RunRecordHisQuery;

import java.util.List;

/**
 * Created by User on 2018/3/10.
 */
public interface RunRecordHisService extends UserServiceCommand<RunRecordHis>{

    List<RunRecordHis> selectRunRecorHisByCondition(RunRecordHisQuery runRecordHisQuery);


    RunRecordHisLog insertRecordHisLog(RunRecordHis runRecordHis);

    Integer  deleteHisById(Long id);
}
