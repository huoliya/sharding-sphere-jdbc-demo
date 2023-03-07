package com.sinocontrol.sharding.sphere.jdbc.demo.task;


import com.sinocontrol.sharding.sphere.jdbc.demo.domain.entity.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.util.CommonMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ConsumeSourceLog {
    private static final Logger log = LoggerFactory.getLogger(ConsumeSourceLog.class);
    private final List<InspMesModel> messages = new ArrayList<>();
    private static Date lastTime = new Date();

    @Autowired
    private CommonMethod commonMethod;
    @Autowired
    private DistributeIotData distributeIotData;

    @RabbitListener(queues = "mongo.data")
    private void process(byte[] byteMessage) {
        try {

            Date nowTime = new Date();
            InspMesModel inspMes = commonMethod.parseMessage(byteMessage);
            if (inspMes == null || inspMes.value == null)
                return;

            messages.add(inspMes);
            if (nowTime.getTime() - lastTime.getTime() > 10000 || messages.size() > 10000) {
                log.info(" process  data :" + messages.size());
                distributeIotData.processData(messages);
                lastTime = new Date();
                messages.clear();
            }
        } catch (Exception e) {
            log.error("ipms : data-process {}: ", e);
        }
    }
}
