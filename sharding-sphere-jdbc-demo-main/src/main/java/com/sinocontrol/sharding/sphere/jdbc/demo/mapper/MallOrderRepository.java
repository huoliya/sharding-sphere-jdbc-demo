package com.sinocontrol.sharding.sphere.jdbc.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.sinocontrol.sharding.sphere.jdbc.demo.config.DBConstants;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;

@DS(value = DBConstants.SHARDING)
public interface MallOrderRepository extends CommonMapper<InspMesModel> {
}