package com.sinocontrol.sharding.sphere.jdbc.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.entity.InspMesModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class CommonMethod {
    private static final Logger log = LoggerFactory.getLogger(CommonMethod.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference tf_im = new TypeReference<InspMesModel>() {
    };
    public InspMesModel parseMessage(byte[] byteMessage) {
        String message = null;
        message = new String(byteMessage, StandardCharsets.UTF_8);
        if (message == null) {
            log.error("parse message byte error.");
            return null;
        }
        InspMesModel inspMes = null;
        try {
            Map<String, Object> info = (Map<String, Object>) JsonUtil.getObjectMapper().readValue(message, JsonUtil.tf_mso);
            inspMes = (InspMesModel) objectMapper.readValue(info.get("l").toString(), tf_im);
            if (info.containsKey("c") && info.get("c") != null)
                inspMes.deviceCode = info.get("c").toString();
            else
                inspMes.deviceCode = "main";
        } catch (Exception e) {
            e.printStackTrace();
            log.error("process message error. message:{} e:{}", message, e);
        }
        return inspMes;
    }
}
