package com.sinocontrol.sharding.sphere.jdbc.demo.config.sharding;


import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

@Slf4j
@Component
public class TimeShardingAlgorithm implements StandardShardingAlgorithm<Date> {

    /**
     * 分片时间格式
     */
    private static final DateTimeFormatter TABLE_SHARD_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHH");

    /**
     * 完整时间格式
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");

    /**
     * 表分片符号，例：t_contract_202201 中，分片符号为 "_"
     */
    private final String TABLE_SPLIT_SYMBOL = "_";


    /**
     * 精准分片
     *
     * @param tableNames           对应分片库中所有分片表的集合
     * @param preciseShardingValue 分片键值，其中 logicTableName 为逻辑表，columnName 分片键，value 为从 SQL 中解析出来的分片键的值
     * @return 表名
     */
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Date> preciseShardingValue) {
        String logicTableName = preciseShardingValue.getLogicTableName();
        ShardingTableCacheEnum logicTable = ShardingTableCacheEnum.of(logicTableName);
        if (logicTable == null) {
            log.error(">>>>>>>>>> 【ERROR】数据表类型错误，请稍后重试，logicTableNames：{}，logicTableName:{}",
                    ShardingTableCacheEnum.logicTableNames(), logicTableName);
            throw new IllegalArgumentException("数据表类型错误，请稍后重试");
        }

        /// 打印分片信息
       // log.info(">>>>>>>>>> 【INFO】精确分片，节点配置表名：{}，数据库缓存表名：{}", tableNames, logicTable.resultTableNamesCache());

        Date dateTime = preciseShardingValue.getValue();
        String resultTableName = logicTableName + "_" + new SimpleDateFormat("yyyyMMddHH").format(dateTime);
        //dateTime.format(TABLE_SHARD_TIME_FORMATTER);
        // 检查分表获取的表名是否存在，不存在则自动建表
        if (!tableNames.contains(resultTableName)) {
            tableNames.add(resultTableName);
        }
        return ShardingAlgorithmTool.getShardingTableAndCreate(logicTable, resultTableName);
    }

    /**
     * 范围分片
     *
     * @param tableNames         对应分片库中所有分片表的集合
     * @param rangeShardingValue 分片范围
     * @return 表名集合
     */
    @Override
    public Collection<String> doSharding(Collection<String> tableNames, RangeShardingValue<Date> rangeShardingValue) {
        String logicTableName = rangeShardingValue.getLogicTableName();
        ShardingTableCacheEnum logicTable = ShardingTableCacheEnum.of(logicTableName);
        if (logicTable == null) {
            log.error(">>>>>>>>>> 【ERROR】逻辑表范围异常，请稍后重试，logicTableNames：{}，logicTableName:{}",
                    ShardingTableCacheEnum.logicTableNames(), logicTableName);
            throw new IllegalArgumentException("逻辑表范围异常，请稍后重试");
        }

        /// 打印分片信息
        log.info(">>>>>>>>>> 【INFO】范围分片，节点配置表名：{}，数据库缓存表名：{}", tableNames, logicTable.resultTableNamesCache());

        // between and 的起始值
        Range<Date> valueRange = rangeShardingValue.getValueRange();
        boolean hasLowerBound = valueRange.hasLowerBound();
        boolean hasUpperBound = valueRange.hasUpperBound();
        ZoneId zoneId = ZoneId.systemDefault();

        // 获取最大值和最小值
        Set<String> tableNameCache = logicTable.resultTableNamesCache();
        LocalDateTime min = hasLowerBound ? valueRange.lowerEndpoint().toInstant().atZone(zoneId).toLocalDateTime() : getLowerEndpoint(tableNameCache);
        LocalDateTime max = hasUpperBound ? valueRange.upperEndpoint().toInstant().atZone(zoneId).toLocalDateTime() : getUpperEndpoint(tableNameCache);

        // 循环计算分表范围
        Set<String> resultTableNames = new LinkedHashSet<>();
        while (min.isBefore(max) || min.equals(max)) {
            String tableName = logicTableName + TABLE_SPLIT_SYMBOL + min.format(TABLE_SHARD_TIME_FORMATTER);
            resultTableNames.add(tableName);
            min = min.plusMinutes(1);
        }
        return ShardingAlgorithmTool.getShardingTablesAndCreate(logicTable, resultTableNames);
    }

    @Override
    public void init() {

    }

    @Override
    public String getType() {
        return null;
    }

    // --------------------------------------------------------------------------------------------------------------
    // 私有方法
    // --------------------------------------------------------------------------------------------------------------

    /**
     * 获取 最小分片值
     *
     * @param tableNames 表名集合
     * @return 最小分片值
     */
    private LocalDateTime getLowerEndpoint(Collection<String> tableNames) {
        Optional<LocalDateTime> optional = tableNames.stream()
                .map(o -> LocalDateTime.parse(o.replace(TABLE_SPLIT_SYMBOL, "") + ":00:00", DATE_TIME_FORMATTER))
                .min(Comparator.comparing(Function.identity()));
        if (optional.isPresent()) {
            return optional.get();
        } else {
            log.error(">>>>>>>>>> 【ERROR】获取数据最小分表失败，请稍后重试，tableName：{}", tableNames);
            throw new IllegalArgumentException("获取数据最小分表失败，请稍后重试");
        }
    }

    /**
     * 获取 最大分片值
     *
     * @param tableNames 表名集合
     * @return 最大分片值
     */
    private LocalDateTime getUpperEndpoint(Collection<String> tableNames) {
        Optional<LocalDateTime> optional = tableNames.stream()
                .map(o -> LocalDateTime.parse(o.replace(TABLE_SPLIT_SYMBOL, "") + ":00:00", DATE_TIME_FORMATTER))
                .max(Comparator.comparing(Function.identity()));
        if (optional.isPresent()) {
            return optional.get();
        } else {
            log.error(">>>>>>>>>> 【ERROR】获取数据最大分表失败，请稍后重试，tableName：{}", tableNames);
            throw new IllegalArgumentException("获取数据最大分表失败，请稍后重试");
        }
    }
}
