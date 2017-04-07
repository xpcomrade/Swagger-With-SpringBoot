package my.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/12/13.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
@Mapper
public interface TestMapper {

    Map<String, Object> findByMobile(@Param("mobile") String mobile);

    List<Map<String, Object>> querySysGroup();

    List<Map<String, Object>> queryByPage(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
