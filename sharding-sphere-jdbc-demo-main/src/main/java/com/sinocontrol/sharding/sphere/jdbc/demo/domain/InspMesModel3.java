package com.sinocontrol.sharding.sphere.jdbc.demo.domain;

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
@TableName("alarm2")
public class InspMesModel3 extends Model<InspMesModel3> {
    @TableId(value = "id")
    public long id;
    @TableField("add_time")
    public Date addTime;
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
    public Date valueTime;
    @TableField("device_code")
    public String deviceCode;
}
