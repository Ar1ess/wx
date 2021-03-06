package com.softlab.wx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 		工程结构
 *
 * 		com.softlab.wx.web - Controller 层
 * 		com.softlab.wx.core.mapper - 数据操作层 DAO
 * 		com.softlab.wx.core.model - 实体类
 * 		com.softlab.wx.service - 业务逻辑层
 * 		WxApplication - 应用启动类
 * 		application.properties - 应用配置文件，应用启动会自动读取配置
 */
@SpringBootApplication
@MapperScan("com.softlab.wx.core.mapper")
public class WxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxApplication.class, args);
	}

}
