package com.sinocontrol.sharding.sphere.jdbc.demo.service;

import com.sinocontrol.sharding.sphere.jdbc.demo.config.DBConstants;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.MallOrderRepository;
import com.sinocontrol.sharding.sphere.jdbc.demo.task.DistributeIotData;
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
public class ShardingAlarmService {
    private static final Logger log = LoggerFactory.getLogger(ShardingAlarmService.class);
    @Autowired
    private MallOrderRepository orderMapper;
    @Async
    public Future saveLogs(List<InspMesModel> inspMesModels) {
        Long currentTime = new Date().getTime();
        orderMapper.insertBatchSomeColumn(inspMesModels);
        log.info(DBConstants.SHARDING + " : size: {},time : {} ms", inspMesModels.size(), System.currentTimeMillis() - currentTime);
        return new AsyncResult(null);
    }
}
