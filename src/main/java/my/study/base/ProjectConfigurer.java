package my.study.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by xpcomrade on 2017/4/7.
 * Copyright (c) 2017, xpcomrade@gmail.com All Rights Reserved.
 * Description: (项目配置). <br/>
 */
@Configuration
public class ProjectConfigurer extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(ProjectConfigurer.class);

    /**对静态资源处理**/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("/favicon.ico");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                Map map = request.getParameterMap();
                StringBuilder stringBuilder = new StringBuilder();
                for (Object obj : map.keySet()) {
                    stringBuilder
                            .append(obj)
                            .append("=")
                            .append(request.getParameter(obj.toString())).append("&");
                }
                logger.info(request.getRequestURL().toString() + "?" + stringBuilder.toString());

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

            }
        });
    }



}
