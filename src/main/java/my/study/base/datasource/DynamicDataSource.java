package my.study.base.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by xpcomrade on 2016/12/13.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: (动态数据源). <br/>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
