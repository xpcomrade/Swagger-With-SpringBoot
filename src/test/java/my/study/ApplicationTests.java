package my.study;

import my.study.controller.IndexController;
import my.study.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
/**
 * Created by xpcomrade on 2017/4/7.
 * Copyright (c) 2017, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ApplicationTests {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(
                new UserController(),
                new IndexController()
        ).build();
    }

    @Test
    public void testIndexController() throws Exception {
        RequestBuilder request = null;

        request = get("/");
        MvcResult result = null;
        mvc.perform(request);
        System.out.println(result.getResponse().getContentAsString());
    }

    @Test
    public void testUserController() throws Exception {
        RequestBuilder request = null;

        request = get("/users/18516291668");
        MvcResult result = null;
       mvc.perform(request);
        System.out.println(result.getResponse().getContentAsString());
    }

}
