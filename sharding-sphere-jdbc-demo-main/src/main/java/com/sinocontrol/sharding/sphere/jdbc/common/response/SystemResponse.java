package com.sinocontrol.sharding.sphere.jdbc.common.response;

import com.sinocontrol.sharding.sphere.jdbc.common.base.BaseResponse;
import com.sinocontrol.sharding.sphere.jdbc.common.enums.ResponseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description: 系统响应
 * @date 2022-05-09 14:44
 */
@Data
@ApiModel(value = "系统响应")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SystemResponse<T> extends BaseResponse {

    @ApiModelProperty(value = "响应结果")
    private T result;

    @ApiModelProperty(value = "响应条数")
    private Integer amount;

    public SystemResponse(ResponseEnum responseEnum, T result, Integer amount) {
        this.setCode(responseEnum.getValue());
        this.setMsg(responseEnum.getDescription());
        this.result = result;
        this.amount = amount;
    }

    public SystemResponse(ResponseEnum responseEnum) {
        this.setCode(responseEnum.getValue());
        this.setMsg(responseEnum.getDescription());
    }

    /**
     * 响应成功
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> success() {
        return new SystemResponse<>(ResponseEnum.SUCCESS, null, null);
    }

    /**
     * 响应成功
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> success(T result) {
        return new SystemResponse<>(ResponseEnum.SUCCESS, result, 1);
    }

    /**
     * 响应成功
     *
     * @param result
     * @param amount
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> success(T result, Integer amount) {
        return new SystemResponse<>(ResponseEnum.SUCCESS, result, amount);
    }

    /**
     * 响应失败
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> fail(T result) {
        return new SystemResponse<>(ResponseEnum.FAIL, result, 1);
    }

    public static <T> SystemResponse<T> fail() {
        return new SystemResponse<T>(ResponseEnum.FAIL);
    }

    /**
     * 判断并响应
     * @param status
     * @param <T>
     * @return
     */
    public static <T> SystemResponse<T> judge(boolean status){
        if (status){
            return success();
        }
        return fail();
    }

}