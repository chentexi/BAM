package com.trent.web.controller.system;

import com.trent.system.service.ISysLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Trent
 * @Date: 2021/6/24 9:48
 * @program: BAM
 * @Description:
 */
@RestController
@RequiredArgsConstructor
public class SysLoginController{
   private ISysLoginService sysLoginService;
   
}