package com.sinocontrol.sharding.sphere.jdbc.common.base;

import cn.hutool.core.util.ObjectUtil;

import java.util.EnumSet;
import java.util.Objects;

/**
 * @email lintiaoda@suntang.com
 * @description: 基本枚举接口
 * @date 2022-04-24 09:26
 */
public interface IBaseEnum<T> {

    /**
     * 获取值
     *
     * @return
     */
    T getValue();

    /**
     * 获取值的描述
     *
     * @return
     */
    String getDescription();


    /**
     * 根据值获取枚举
     *
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum<?>> E getEnumByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        return EnumSet.allOf(clazz)
                .stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);
    }

    /**
     * 根据文本标签获取值
     *
     * @param value
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum<?>> String getDescriptionByValue(Object value, Class<E> clazz) {
        Objects.requireNonNull(value);
        E matchEnum = EnumSet.allOf(clazz)
                .stream()
                .filter(e -> ObjectUtil.equal(e.getValue(), value))
                .findFirst()
                .orElse(null);
        if (Objects.nonNull(matchEnum)) {
            return matchEnum.getDescription();
        }
        return null;
    }


    /**
     * 根据值获取描述
     *
     * @param description
     * @param clazz
     * @param <E>
     * @return
     */
    static <E extends Enum<E> & IBaseEnum<?>> Object getValueByDescription(String description, Class<E> clazz) {
        Objects.requireNonNull(description);
        E matchEnum = EnumSet.allOf(clazz)
                .stream()
                .filter(e -> ObjectUtil.equal(e.getDescription(), description))
                .findFirst()
                .orElse(null);
        if (Objects.nonNull(matchEnum)) {
            return matchEnum.getValue();
        }
        return null;
    }

}
