package com.trent.user.service.impl;

import com.trent.user.pojo.Department;
import com.trent.user.mapper.DepartmentMapper;
import com.trent.user.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
