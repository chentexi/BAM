package com.trent.system.service.impl.area;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.area.AreaCodeMapper;
import com.trent.system.pojo.area.AreaCode;
import com.trent.system.service.area.IAreaCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Trent
 * @since 2022-01-06
 */
@Service
public class AreaCodeServiceImpl extends ServiceImpl<AreaCodeMapper, AreaCode> implements IAreaCodeService{
	@Autowired
	private AreaCodeMapper areaCodeMapper;
	@Override
	public List<AreaCode> findAreaCodeAll(){
		System.out.println();
		List<AreaCode> areaCodeList = areaCodeMapper.findAreaCodeAll();
		return areaCodeList;
	}

}
