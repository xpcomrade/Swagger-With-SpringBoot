package my.study.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import my.study.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xpcomrade on 2016/1/20.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "获取用户详细信息", notes = "根据手机号码获取用户详细信息")
    @ApiResponse(code = 200, message = "Success", response = Map.class)
    @ApiImplicitParam(name = "mobile", value = "用户手机号", required = true, dataType = "String")
    @RequestMapping(value = "/users/{mobile}", method = RequestMethod.GET)
    Map findByMobile(@PathVariable String mobile) {
        return testService.findByMobile(mobile);
    }

    @ApiOperation(value = "分组", notes = "系统分组列表")
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    List<Map<String, Object>> querySysGroup() {
        return testService.querySysGroup();
    }


    @ApiOperation(value = "用户信息列表", notes = "分页查询用户详细信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "大小", required = false, dataType = "Integer")
    })
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    PageInfo queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize) {

        PageInfo pageInfo = testService.queryByPage(pageNum, pageSize);

        return pageInfo;
    }
}
