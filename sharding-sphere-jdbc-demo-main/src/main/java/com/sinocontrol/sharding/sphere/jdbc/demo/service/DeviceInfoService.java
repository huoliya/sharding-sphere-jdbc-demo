package com.sinocontrol.sharding.sphere.jdbc.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.mapper.MallOrderRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeviceInfoService {

    @Autowired
    private MallOrderRepository orderMapper;

    public List<InspMesModel> findDataByCondition(Date startTime, Date endTime, String code, Integer pageNum, Integer pageSize) {
        QueryWrapper<InspMesModel> wrapper = new QueryWrapper<>();
        if (Strings.isNotBlank(code))
            wrapper.eq("code", code);
        wrapper.gt("time", startTime);
        wrapper.lt("time", endTime);
        // 创建分页对象（1表示第一页；4表示每页大小为4）
        Page<InspMesModel> page = new Page<>(pageNum, pageSize);
        Page<InspMesModel> result = orderMapper.selectPage(page, wrapper);

        return result.getRecords();
    }
}
