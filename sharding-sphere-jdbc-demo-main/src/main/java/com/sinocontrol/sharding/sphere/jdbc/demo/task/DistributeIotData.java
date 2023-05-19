package com.sinocontrol.sharding.sphere.jdbc.demo.task;

import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.MallOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
public class DistributeIotData {
    private static final Logger log = LoggerFactory.getLogger(DistributeIotData.class);
    @Autowired
    private MallOrderRepository orderMapper;

    public void processData(List<InspMesModel> sourceLogs) {
        Long currentTime = new Date().getTime();
        sourceLogs.forEach(p -> {
            p.time = new Timestamp(p.valueTime * 1000);
        });
        try {
            orderMapper.insertBatchSomeColumn(sourceLogs);
        } catch (Exception e) {
            log.error("processData" + e);
        }
        log.info("size: {},time : {} ms", sourceLogs.size(), System.currentTimeMillis() - currentTime);
    }


}