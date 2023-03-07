package com.sinocontrol.sharding.sphere.jdbc.common.constant;

/**
 * @description: 全局常量
 * @date 2022-05-09 14:16
 */
public interface Constants {

    /**
     * 未知
     */
    String UNKNOWN = "unknown";

    /**
     * 本机IP
     */
    String LOCALHOST_IP = "127.0.0.1";


    /**
     * 和符号相关的常量
     */
    interface Symbol {

        /**
         * 中划线
         */
        String STRIKETHROUGH = "-";

        /**
         * 逗号分隔符
         */
        String DELIMITER_COMMA = ",";

    }


    /**
     * 和时间相关的常量
     */
    interface Time {

        /**
         * 年月表达式
         */
        String YEAR_MONTH_PATTERN = "yyyy-MM";

        /**
         * 年月日表达式
         */
        String DATE_PATTERN = "yyyy-MM-dd";

        /**
         * 年月日时分秒表达式
         */
        String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    }

    /**
     * 和数字相关的常量
     */
    interface Number {

        Integer ZERO = 0;

        Integer ONE = 1;

        Integer TWO = 2;

        Integer THREE = 3;

        Integer FOUR = 4;

        Integer FIVE = 5;

        Integer SIX = 6;

        Integer SEVEN = 7;

        Integer EIGHT = 8;

        Integer NINE = 9;

        Integer TEN = 10;

        Integer FIFTEEN = 15;

        Integer HUNDRED = 100;

        Integer THOUSAND = 1000;
    }


}
