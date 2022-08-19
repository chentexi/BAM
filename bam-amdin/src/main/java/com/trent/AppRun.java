package com.trent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Trent
 * @Date: 2021/6/22 17:05
 * @program: BAM
 * @Description:
 */
@SpringBootApplication
@ComponentScan("com.trent.common.utils.*")
@MapperScan("com.*.*.mapper")
public class AppRun{
	public static void main(String[] args){
		SpringApplication.run(AppRun.class, args);
	}
}


