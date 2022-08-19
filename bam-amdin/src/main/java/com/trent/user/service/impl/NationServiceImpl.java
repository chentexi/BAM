package com.trent.user.service.impl;

import com.trent.user.pojo.Nation;
import com.trent.user.mapper.NationMapper;
import com.trent.user.service.INationService;
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
