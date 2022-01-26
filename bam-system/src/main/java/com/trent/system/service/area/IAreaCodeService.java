package com.trent.system.service.area;


import com.baomidou.mybatisplus.extension.service.IService;
import com.trent.system.pojo.area.AreaCode;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Trent
 * @since 2022-01-06
 */
public interface IAreaCodeService extends IService<AreaCode>{
	
	List<AreaCode> findAreaCodeAll();
	
}
