package com.sinocontrol.sharding.sphere.jdbc.demo.mapper2;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sinocontrol.sharding.sphere.jdbc.demo.config.DBConstants;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel2;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.CommonMapper;

@DS(value = DBConstants.STARROCKS)
public interface AlarmLogPlusMapper extends CommonMapper<InspMesModel2> {
}
