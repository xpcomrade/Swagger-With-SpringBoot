package my.study.mock;

import my.study.controller.UserController;
import my.study.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by xpcomrade on 2017/4/10.
 * Copyright (c) 2017, xpcomrade@gmail.com All Rights Reserved.
 * Description: (UserController Tests). <br/>
 */
// tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    RequestBuilder request = null;

    @Test
    public void findByMobile() throws Exception {
        request = get("/users/{mobile}", "18516291668");
        this.mockMvc.perform(request).andDo(print());
    }

    @Test
    public void querySysGroup() throws Exception {
        this.mockMvc.perform(get("/groups").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print());
    }

}
