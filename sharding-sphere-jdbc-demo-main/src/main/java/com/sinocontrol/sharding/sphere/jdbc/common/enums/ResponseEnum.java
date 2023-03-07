package com.sinocontrol.sharding.sphere.jdbc.common.enums;

import com.sinocontrol.sharding.sphere.jdbc.common.base.IBaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @email lintiaoda@suntang.com
 * @description: 响应枚举
 * @date 2022-05-09 14:40
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements IBaseEnum<Integer> {

    /**
     * 枚举所有响应
     */
    SUCCESS(1000, "成功"),

    FAIL(2000, "系统异常"),

    ;
    private final Integer value;

    private final String description;


}
