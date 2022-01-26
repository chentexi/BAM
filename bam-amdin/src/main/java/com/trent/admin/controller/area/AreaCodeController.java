package com.trent.admin.controller.area;


import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.area.AreaCode;
import com.trent.system.service.area.IAreaCodeService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Trent
 * @since 2022-01-06
 */
@RestController
@RequestMapping("/areaCode")
public class AreaCodeController{
	@Autowired
	private IAreaCodeService areaCodeService;
	
	@ApiModelProperty("查询mybatis")
	@PostMapping("findAreaCodeAll")
	public ResultUtil findAreaCodeAll(){
		List<AreaCode> areaCodeList=areaCodeService.findAreaCodeAll();
		return ResultUtil.ok(areaCodeList);
	}
}
