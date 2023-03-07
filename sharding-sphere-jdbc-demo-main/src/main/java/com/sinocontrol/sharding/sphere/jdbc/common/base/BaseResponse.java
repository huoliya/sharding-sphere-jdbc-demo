package com.sinocontrol.sharding.sphere.jdbc.common.base;


import com.sinocontrol.sharding.sphere.jdbc.common.enums.ResponseEnum;
import com.sinocontrol.sharding.sphere.jdbc.common.util.TimeStampUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @email lintiaoda@suntang.com
 * @description: 基本响应实体
 * @date 2022-05-09 14:21
 */
@Data
@ApiModel(value = "基本响应实体")
@NoArgsConstructor
public class BaseResponse {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "响应信息")
    private String msg;

    @ApiModelProperty(value = "响应时间")
    private Long timestamp = TimeStampUtil.getCurrentTimeStamp();


    public BaseResponse(ResponseEnum responseEnum) {
        this.code = responseEnum.getValue();
        this.msg = responseEnum.getDescription();
        this.timestamp = TimeStampUtil.getCurrentTimeStamp();
    }

    public BaseResponse(ResponseEnum responseEnum, String msg) {
        this.code = responseEnum.getValue();
        this.msg = msg;
        this.timestamp = TimeStampUtil.getCurrentTimeStamp();
    }

    public static BaseResponse isFailed(String exceptions) {
        return new BaseResponse(ResponseEnum.FAIL, exceptions);
    }

    public static BaseResponse isFailed(ResponseEnum responseEnum,String exceptions) {
        return new BaseResponse(responseEnum, exceptions);
    }

    public static BaseResponse isSuccessful() {
        return new BaseResponse(ResponseEnum.SUCCESS);
    }


}
