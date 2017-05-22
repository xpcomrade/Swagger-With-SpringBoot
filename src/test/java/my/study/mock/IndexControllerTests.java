package my.study.mock;

import my.study.controller.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by xpcomrade on 2017/4/10.
 * Copyright (c) 2017, xpcomrade@gmail.com All Rights Reserved.
 * Description: (IndexController Tests). <br/>
 */
// tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void index() throws Exception {
        this.mvc.perform(get("/index")).andDo(new ResultHandler() {
            @Override
            public void handle(MvcResult result) throws Exception {
                System.out.println(
                        "StatusCode -> " + result.getResponse().getStatus()
                                + ",responseBody -> " + result.getResponse().getContentAsString()
                );
            }
        });
    }


}
