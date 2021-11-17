package com.trent.system.pojo.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author: Trent
 * @Date: 2021/11/14 17:34
 * @program: BAM
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdminLogin对象" ,description = "")
public class AdminLoginParam{
	@ApiModelProperty(value = "用户名",required = true)
	private String userName;
	@ApiModelProperty(value = "密码",required = true)
	private String passWord;
	@ApiModelProperty(value = "验证码",required = true)
	private String captcha;
}
