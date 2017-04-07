package my.study.base.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by xpcomrade on 2016/12/13.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (切换数据源Advice). <br/>
 */
@Aspect
@Order(-1)// 保证在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds){
        if(!DynamicDataSourceContextHolder.containsDataSource(ds.name())){
            logger.error("DataSource [{}] not found，use the default DataSource > {}", ds.name(), point.getSignature());
        } else {
            logger.debug("Use DataSource : {} > {}", ds.name(), point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceType(ds.name());
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds){
        logger.debug("Revert DataSource : {} > {}", ds.name(), point.getSignature());
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
