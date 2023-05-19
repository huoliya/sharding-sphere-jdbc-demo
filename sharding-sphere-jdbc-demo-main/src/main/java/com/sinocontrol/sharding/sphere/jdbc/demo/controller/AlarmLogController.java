package com.sinocontrol.sharding.sphere.jdbc.demo.controller;

import com.sinocontrol.sharding.sphere.jdbc.demo.domain.AlarmLogModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.service.AlarmLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmLogController {
    @Autowired
    private AlarmLogService alarmLogService;

    @RequestMapping(value = "/saveAlarmLog", method = RequestMethod.POST)
    public int saveAlarmLog(@RequestBody AlarmLogModel alarmLogModel) {
        return alarmLogService.saveAlarmLog(alarmLogModel);
    }
}
