package my.study.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import my.study.base.datasource.TargetDataSource;
import my.study.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/12/13.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public Map<String, Object> findByMobile(String mobile) {
        return testMapper.findByMobile(mobile);
    }

    @TargetDataSource(name = "ds1")
    public List<Map<String, Object>> querySysGroup(){
        return testMapper.querySysGroup();
    }

    public PageInfo queryByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = testMapper.queryByPage(pageNum, pageSize);
        return new PageInfo(list);
    };
}
