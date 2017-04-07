package my.study.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by xpcomrade on 2016/1/20.
 * Copyright (c) 2016, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */
@RestController
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String index() {
        return "Hello, world";
    }

}
