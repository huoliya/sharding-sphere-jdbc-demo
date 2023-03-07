package com.sinocontrol.sharding.sphere.jdbc.common.util;

/**
 * @description:
 * @date 2022-05-09 14:52
 */

import com.sinocontrol.sharding.sphere.jdbc.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


/**
 * @description: 时间戳工具类
 * @date 2022-05-09 14:24
 */
public class TimeStampUtil {

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static Long getCurrentTimeStamp() {
        return System.currentTimeMillis() / Constants.Number.THOUSAND;
    }

    /**
     * 获取当前日期的时间戳
     *
     * @return
     */
    public static Long getCurrentDateTimeStamp() throws ParseException {
        return date2TimeStamp(null);
    }

    /**
     * 获取当天时间字符串
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return new SimpleDateFormat(Constants.Time.DATE_PATTERN).format(new Date());
    }


    /**
     * 时间戳转换成日期时间格式字符串
     * 1646989340 -> 2022-03-11 17:02:20
     *
     * @param timeStamp
     * @return
     */
    public static String timeStamp2DateTimeStr(Long timeStamp) {
        if (Objects.isNull(timeStamp)) {
            return "";
        }
        return new SimpleDateFormat(Constants.Time.DATETIME_PATTERN).format(new Date(Long.parseLong(timeStamp + "000")));
    }


    /**
     * 时间戳转换成日期格式字符串(只取日期部分)
     * 1646989340 -> 2022-03-11
     *
     * @param timeStamp
     * @return
     */
    public static String timeStamp2DateStr(Long timeStamp) {
        if (Objects.isNull(timeStamp)) {
            return "";
        }
        return new SimpleDateFormat(Constants.Time.DATE_PATTERN).format(new Date(Long.parseLong(timeStamp + "000")));
    }

    public static String timeStamp2YearMonth(Long timeStamp) {
        if (Objects.isNull(timeStamp)) {
            return "";
        }
        return new SimpleDateFormat(Constants.Time.YEAR_MONTH_PATTERN).format(new Date(Long.parseLong(timeStamp + "000")));
    }

    /**
     * long类型时间戳转Date类型
     *
     * @param timeStamp
     * @return
     */
    public static Date timeStamp2Date(Long timeStamp) {
        if (timeStamp.toString().length() == Constants.Number.TEN) {
            timeStamp = timeStamp * Constants.Number.THOUSAND;
        }
        return new Date(timeStamp);
    }


    /**
     * 获取具体某一个日期的时间戳
     * 传入为null或者空字符串的话，默认返回当天的吧
     *
     * @param dateStr
     * @return
     * @throws ParseException eg. 2022-03-01 -> 1646064000
     */
    public static Long date2TimeStamp(String dateStr) throws ParseException {
        if (StringUtils.isBlank(dateStr)) {
            // 未传入 默认返回当天的吧
            return date2TimeStamp(getCurrentDateStr());
        }
        return new SimpleDateFormat(Constants.Time.DATE_PATTERN).parse(dateStr).getTime() / Constants.Number.THOUSAND;
    }


}
