package com.sinocontrol.sharding.sphere.jdbc.demo.mapper3;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sinocontrol.sharding.sphere.jdbc.demo.config.DBConstants;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel3;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.CommonMapper;

@DS(value = DBConstants.MYSQL)
public interface AlarmLogPlusMysqlMapper extends CommonMapper<InspMesModel3> {
}
