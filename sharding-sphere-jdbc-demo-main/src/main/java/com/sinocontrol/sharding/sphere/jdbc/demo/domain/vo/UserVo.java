package com.sinocontrol.sharding.sphere.jdbc.demo.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserVo {

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("电子邮箱")
    private String email;

}
