package com.trent.user.service.impl;

import com.trent.user.pojo.MailLog;
import com.trent.user.mapper.MailLogMapper;
import com.trent.user.service.IMailLogService;
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
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
