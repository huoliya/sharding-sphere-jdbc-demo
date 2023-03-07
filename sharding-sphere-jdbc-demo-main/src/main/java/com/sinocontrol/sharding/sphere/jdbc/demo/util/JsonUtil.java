package com.sinocontrol.sharding.sphere.jdbc.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonUtil {
    private static ObjectMapper objectMapper = null;
    public static TypeReference tf_o = new TypeReference<Object>(){};
    public static TypeReference tf_mso = new TypeReference<Map<String, Object>>(){};

    public static ObjectMapper getObjectMapper() {
        if (objectMapper != null)
            return objectMapper;
        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper;
    }
}
