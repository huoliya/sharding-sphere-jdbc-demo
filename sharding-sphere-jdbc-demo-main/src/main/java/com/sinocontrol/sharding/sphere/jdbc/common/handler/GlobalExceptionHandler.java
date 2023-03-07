package com.sinocontrol.sharding.sphere.jdbc.common.handler;

import com.sinocontrol.sharding.sphere.jdbc.common.base.BaseResponse;
import com.sinocontrol.sharding.sphere.jdbc.common.response.SystemResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;

/**
 * @description: 全局异常处理类
 * @date 2022-03-24 15:15
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public BaseResponse exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("接口:{}，发生异常，异常摘要：{}", request.getRequestURI(), e.getMessage(), e);
        return SystemResponse.fail();
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public BaseResponse bindExceptionHandler(HttpServletRequest request, BindException e) {
        String errorMessage = getValidErrorMessage(e.getBindingResult());
        log.error("接口:{}，发生参数校验异常，异常摘要:{}", request.getRequestURI(), errorMessage);
        if (StringUtils.isNotBlank(errorMessage)) {
            return SystemResponse.fail(errorMessage);
        }
        return SystemResponse.fail();
    }


    /**
     * 处理请求参数格式错误
     *
     * @param request
     * @param e
     * @return
     * @RequestBody 上使用@Valid实体上使用@NotNull等，验证失败后抛出的异常是MethodArgumentNotValidException异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e) {
        String errorMessage = getValidErrorMessage(e.getBindingResult());
        log.error("接口:{}，发生参数校验异常，异常摘要:{}", request.getRequestURI(), errorMessage);
        if (StringUtils.isNotBlank(errorMessage)) {
            return SystemResponse.fail(errorMessage);
        }
        return SystemResponse.fail();
    }

    /**
     * 获取@Valid、@Validated的异常信息
     * @param e
     * @return
     */
    private String getValidErrorMessage(BindingResult e) {
        return e.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }

}
