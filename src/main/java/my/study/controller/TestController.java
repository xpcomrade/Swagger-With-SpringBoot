package my.study.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
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

    @ApiOperation(value = "users", nickname = "users")
    @ApiResponse(code = 200, message = "Success", response = Map.class)
    @ApiImplicitParam(name = "mobile", required = true, dataType = "String")
    @RequestMapping(value = "/users/{mobile}", method = RequestMethod.GET)
    Map findByMobile(@PathVariable String mobile) {
        return testService.findByMobile(mobile);
    }

    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    List<Map<String, Object>> querySysGroup() {
        return testService.querySysGroup();
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    PageInfo queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                            @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo pageInfo = testService.queryByPage(pageNum, pageSize);

        return pageInfo;
    }
}
