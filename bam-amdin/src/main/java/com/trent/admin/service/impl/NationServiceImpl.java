package com.trent.admin.service.impl;

import com.trent.admin.pojo.Nation;
import com.trent.admin.mapper.NationMapper;
import com.trent.admin.service.INationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements INationService {

}
