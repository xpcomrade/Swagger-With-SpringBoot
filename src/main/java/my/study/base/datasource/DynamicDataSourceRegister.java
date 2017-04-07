package my.study.base.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/12/13.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (注册动态数据源). <br/>
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    // 如配置文件中未指定数据源类型，使用该默认值
    private static final String DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

    private ConversionService conversionService = new DefaultConversionService();
    // 数据源
    private DataSource defaultDataSource;

    private PropertyValues dataSourcePropertyValues;

    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    @Override
    public void setEnvironment(Environment environment) {
        initDefaultDataSource(environment);
        initCustomDataSources(environment);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);

        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);

        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry Successfully...");
    }

    private void initDefaultDataSource(Environment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        String type = propertyResolver.getProperty("type");
        String driverClassName = propertyResolver.getProperty("driver-class-name");
        String url = propertyResolver.getProperty("url");
        String username = propertyResolver.getProperty("username");
        String password = propertyResolver.getProperty("password");

        defaultDataSource = buildDataSource(type, driverClassName, url, username, password);
        dataBinder(defaultDataSource, env);
    }

    private void initCustomDataSources(Environment env) {
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "custom.datasource.");
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {
            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            String driverClassName = dsMap.get("driver-class-name").toString();
            String type = dsMap.get("type").toString();
            String url = dsMap.get("url").toString();
            String username = dsMap.get("username").toString();
            String password = dsMap.get("password").toString();

            DataSource ds = buildDataSource(type, driverClassName, url, username, password);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, env);
        }
    }

    private void dataBinder(DataSource dataSource, Environment env) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if(dataSourcePropertyValues == null){
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
            Map<String, Object> values = new HashMap<String, Object>(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    /**
     *  构建数据源
     * @param type
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    @SuppressWarnings("unchecked")
    private DataSource buildDataSource(String type, String driverClassName, String url, String username, String password){

        try {
            if (StringUtils.isEmpty(type)) {
                type = DATASOURCE_TYPE_DEFAULT;
            }

            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(type);

            DataSourceBuilder factory = DataSourceBuilder.create()
                    .driverClassName(driverClassName)
                    .url(url)
                    .username(username)
                    .password(password)
                    .type(dataSourceType);


            return factory.build();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
