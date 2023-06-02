package com.sinocontrol.sharding.sphere.jdbc.demo.service;

import com.sinocontrol.sharding.sphere.jdbc.demo.config.DBConstants;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel3;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper3.AlarmLogPlusMysqlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class MysqlAlarmSevice {
    private static final Logger log = LoggerFactory.getLogger(MysqlAlarmSevice.class);
    @Autowired
    private AlarmLogPlusMysqlMapper alarmLogPlusMysqlMapper;
    @Async
    public Future saveLogs(List<InspMesModel3> inspMesModels) {
        Long currentTime = new Date().getTime();
        alarmLogPlusMysqlMapper.insertBatchSomeColumn(inspMesModels);
        log.info(DBConstants.SHARDING + " : size: {},time : {} ms", inspMesModels.size(), System.currentTimeMillis() - currentTime);
        return new AsyncResult(null);
    }
}
