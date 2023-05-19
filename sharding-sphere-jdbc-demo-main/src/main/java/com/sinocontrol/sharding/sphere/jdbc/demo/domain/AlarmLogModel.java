package com.sinocontrol.sharding.sphere.jdbc.demo.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("alarm_log")
public class AlarmLogModel extends Model<AlarmLogModel> {
    @TableId(value = "id", type = IdType.AUTO)
    public String id;

    @TableField("add_time")
    public Date addTime;

    @TableField("node_id")
    public Integer node_id;

    @TableField("loop")
    public String loop;

    @TableField("loop_id")
    public Integer loopId;

    @TableField("alarm_message")
    public String alarm_message;

    @TableField("alarm_type")
    public String alarm_type;

    @TableField("alarm_time")
    public Date alarm_time;

    @TableField("alarm_severity")
    public String alarm_severity;

}
