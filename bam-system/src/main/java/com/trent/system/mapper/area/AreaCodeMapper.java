package com.trent.system.mapper.area;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trent.system.pojo.area.AreaCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Trent
 * @since 2022-01-06
 */
@Repository
public interface AreaCodeMapper extends BaseMapper<AreaCode>{
	
	List<AreaCode> findAreaCodeAll();
	
	
}
