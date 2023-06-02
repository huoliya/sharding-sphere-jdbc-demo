package com.sinocontrol.sharding.sphere.jdbc.demo.task;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinocontrol.sharding.sphere.jdbc.demo.config.DBConstants;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel2;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel3;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.MallOrderRepository;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper2.AlarmLogPlusMapper;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper3.AlarmLogPlusMysqlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DistributeIotData {
    private static final Logger log = LoggerFactory.getLogger(DistributeIotData.class);
    @Autowired
    private MallOrderRepository orderMapper;
    @Autowired
    private AlarmLogPlusMapper alarmLogPlusMapper;
    @Autowired
    private AlarmLogPlusMysqlMapper alarmLogPlusMysqlMapper;

    public void processData(List<InspMesModel> sourceLogs) {
        Long currentTime = new Date().getTime();
        sourceLogs.forEach(p -> {
            p.time = new Timestamp(p.valueTime * 1000);
        });
        try {
            List<InspMesModel2> inspMesModel2List = new ArrayList<>();
            List<InspMesModel3> inspMesModel3List = new ArrayList<>();
            sourceLogs.forEach(p->{
                InspMesModel2 inspMesModel2 = new InspMesModel2();
                InspMesModel3 inspMesModel3 = new InspMesModel3();
                inspMesModel2.addTime = new Date(p.addTime * 1000);
                inspMesModel2.code = p.code;
                inspMesModel2.codeType = p.codeType;
                inspMesModel2.valueTime = new Date(p.valueTime * 1000);
                inspMesModel2.deviceCode = p.deviceCode;
                inspMesModel2.id = 1;
                inspMesModel2.valueQuality = "192";
                inspMesModel2.sourceType = p.sourceType;
                inspMesModel2.loopId = 1;
                inspMesModel2.value = p.value;

                inspMesModel3.addTime = new Date(p.addTime * 1000);
                inspMesModel3.code = p.code;
                inspMesModel3.codeType = p.codeType;
                inspMesModel3.valueTime = new Date(p.valueTime * 1000);
                inspMesModel3.deviceCode = p.deviceCode;
                inspMesModel3.id = 1;
                inspMesModel3.valueQuality = "192";
                inspMesModel3.sourceType = p.sourceType;
                inspMesModel3.loopId = 1;
                inspMesModel3.value = p.value;

                inspMesModel2List.add(inspMesModel2);
                inspMesModel3List.add(inspMesModel3);
            });

            orderMapper.insertBatchSomeColumn(sourceLogs);
            log.info(DBConstants.SHARDING + " : size: {},time : {} ms", sourceLogs.size(), System.currentTimeMillis() - currentTime);
            currentTime = System.currentTimeMillis();
            alarmLogPlusMapper.insertBatchSomeColumn(inspMesModel2List);
            log.info(DBConstants.STARROCKS + " : size: {},time : {} ms", sourceLogs.size(), System.currentTimeMillis() - currentTime);
            currentTime = System.currentTimeMillis();
            alarmLogPlusMysqlMapper.insertBatchSomeColumn(inspMesModel3List);
            log.info(DBConstants.MYSQL + " : size: {},time : {} ms", sourceLogs.size(), System.currentTimeMillis() - currentTime);
        } catch (Exception e) {
            log.error("processData" + e);
        }
        log.info("size: {},time : {} ms", sourceLogs.size(), System.currentTimeMillis() - currentTime);
    }


}