package my.study;

import my.study.base.datasource.DynamicDataSourceRegister;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * Created by xpcomrade on 2016/1/20.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (程序入口). <br/>
 */
@SpringBootApplication(scanBasePackages = {"my.study"})
@Import({DynamicDataSourceRegister.class})
public class Application {

    public static void main(String[] args) {
        // Fluent builder API
     /*   new SpringApplicationBuilder()
                .addCommandLineProperties(false)
                .bannerMode(Banner.Mode.OFF)
                .sources(Application.class)
                .properties("spring.config.name=myproject")
                .run(args);*/

        //SpringApplication.run(Application.class, args);

   /*     System.setProperty("spring.config.name", "myApplication");
        System.setProperty("spring.config.location", "classpath:/config/application.yml");*/

        SpringApplication app = new SpringApplication(Application.class);
        app.setAddCommandLineProperties(false);
        app.setBannerMode(Banner.Mode.OFF);
        app.setRegisterShutdownHook(true);

        ApplicationContext ctx = app.run(args);

    }
}
