package my.study.base.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xpcomrade on 2016/12/13.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (MyBatis扫描接口). <br/>
 */
@Configuration
@AutoConfigureAfter({MyBatisConfiguration.class})
public class MyBatisMapperScannerConfiguration {

    @Bean(name = "mapperScannerConfigurer")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("my.study.mapper");
        return mapperScannerConfigurer;
    }
}
