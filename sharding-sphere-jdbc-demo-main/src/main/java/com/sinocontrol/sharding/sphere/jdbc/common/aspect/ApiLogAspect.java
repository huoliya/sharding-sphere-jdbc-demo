package com.sinocontrol.sharding.sphere.jdbc.common.aspect;

import cn.hutool.core.thread.threadlocal.NamedThreadLocal;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinocontrol.sharding.sphere.jdbc.common.util.IpUtil;
import com.sinocontrol.sharding.sphere.jdbc.common.util.TimeStampUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @date 2022-05-09 18:10
 */
@Slf4j
@Aspect
@Component
public class ApiLogAspect {

    private static final ThreadLocal<Long> START_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocal StartTime");

    public static final String TRACE_ID = "TRACE_ID";

    @Pointcut("execution(* com.sinocontrol.sharding.*..controller.*..*(..))")
    public void apiLog() {
    }

    @Before("apiLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        MDC.put(TRACE_ID, IdUtil.simpleUUID());
        long beginTime = System.currentTimeMillis();
        START_TIME_THREAD_LOCAL.set(beginTime);
        HttpServletRequest request = currentRequest();
        Map<String, String> headerInfo = getHeadersInfo(request);
        ObjectMapper mapper = new ObjectMapper();
        List<Object> objectList = Arrays.stream(joinPoint.getArgs())
                .collect(Collectors.toList())
                .stream().filter(r -> !(r instanceof ServletResponse || r instanceof MultipartFile || r instanceof MultipartFile[]))
                .collect(Collectors.toList());
        log.info(
                "\n<================{}================>" +
                        "\n==>  URL: {}" +
                        "\n==>  HTTP_METHOD: {}" +
                        "\n==>  CLASS_METHOD: {}" +
                        "\n==>  SESSION_ID: {}" +
                        "\n==>  IP: {}" +
                        "\n==>  REQUEST_TIME: {}" +
                        "\n==>  PARAM: {}" +
                        "\n==>  HEADER: {}" +
                        "\n<================{}================>"
                , MDC.get(TRACE_ID)
                , request.getRequestURI()
                , request.getMethod()
                , joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                , request.getSession().getId()
                , IpUtil.getIpAddress(request)
                , TimeStampUtil.timeStamp2DateTimeStr(TimeStampUtil.getCurrentTimeStamp())
                , mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectList)
                , mapper.writerWithDefaultPrettyPrinter().writeValueAsString(headerInfo)
                , MDC.get(TRACE_ID)
        );
    }

    @AfterReturning(returning = "ret", pointcut = "apiLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        long beginTime = START_TIME_THREAD_LOCAL.get();
        ObjectMapper mapper = new ObjectMapper();
        log.info(
                "\n<================{}================>" +
                        "\n==>  已响应,耗时 : {}," +
                        "\n==>  RESPONSE : {}" +
                        "\n<================{}================>",
                MDC.get(TRACE_ID),
                this.formatDateTime(System.currentTimeMillis() - beginTime),
                // 响应内容写出来的话，有点多呀，算了，暂时不打印
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ret),
                MDC.get(TRACE_ID)
        );
        START_TIME_THREAD_LOCAL.remove();
        MDC.clear();
    }

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(60);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    private String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }

    private HttpServletRequest currentRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }
}