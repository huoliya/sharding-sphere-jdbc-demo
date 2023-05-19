package com.sinocontrol.sharding.sphere.jdbc.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import groovy.transform.EqualsAndHashCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("alarm")
public class InspMesModel extends Model<InspMesModel> {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    public String id;
    @TableField("add_time")
    public Long addTime;
    @TableField("code")
    public String code;
    @TableField("code_type")
    public String codeType;
    @TableField("loop_id")
    public Integer loopId;
    @TableField("source_type")
    public Integer sourceType;
    @TableField("value")
    public String value;
    @TableField("value_quality")
    public String valueQuality;
    @TableField("value_time")
    public Long valueTime;
    @TableField("time")
    public Timestamp time;
    @TableField("device_code")
    public String deviceCode;
}
