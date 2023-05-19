package com.sinocontrol.sharding.sphere.jdbc.demo.controller;

import com.sinocontrol.sharding.sphere.jdbc.demo.domain.InspMesModel;
import com.sinocontrol.sharding.sphere.jdbc.demo.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class DeviceInfoController {
    @Autowired
    private DeviceInfoService deviceInfoService;

    @RequestMapping(value = "/deviceInfo", method = RequestMethod.GET)
    public List<InspMesModel> getItems(Date startTime, Date endTime, String code, Integer pageNum, Integer pageSize) {
        return deviceInfoService.findDataByCondition(startTime, endTime, code, pageNum, pageSize);
    }

}
