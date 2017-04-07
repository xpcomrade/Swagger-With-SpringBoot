package my.study.base.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by xpcomrade on 2017/4/6.
 * Copyright (c) 2017, xpcomrade@gmail.com All Rights Reserved.
 * Description: (Swagger配置类). <br/>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("my.study.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo () {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多信息请关注：https://github.com/xpcomrade")
                .contact(new Contact("xpcomrade", "https://github.com/xpcomrade", "xpcomrade@gmail.com"))
                .version("1.0")
                .build();

    }
}
