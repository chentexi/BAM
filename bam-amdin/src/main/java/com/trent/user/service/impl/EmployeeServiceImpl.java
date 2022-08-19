package com.trent.user.service.impl;

import com.trent.user.pojo.Employee;
import com.trent.user.mapper.EmployeeMapper;
import com.trent.user.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
