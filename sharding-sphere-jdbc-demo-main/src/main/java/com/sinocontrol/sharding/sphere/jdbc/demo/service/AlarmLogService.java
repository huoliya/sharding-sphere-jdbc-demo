package com.sinocontrol.sharding.sphere.jdbc.demo.service;

import com.sinocontrol.sharding.sphere.jdbc.demo.domain.AlarmLogModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.AlarmLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmLogService {
    @Autowired
    private AlarmLogMapper alarmLogMapper;

    public int saveAlarmLog(AlarmLogModel alarmLogModel) {
       return alarmLogMapper.insert(alarmLogModel);
    }
}
