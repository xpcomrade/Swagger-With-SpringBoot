package my.study.template;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xpcomrade on 2017/4/7.
 * Copyright (c) 2017, xpcomrade@gmail.com All Rights Reserved.
 * Description: TODO(这里用一句话描述这个类的作用). <br/>
 */

// tells JUnit to run using Spring’s testing support. SpringRunner is the new name for SpringJUnit4ClassRunner
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIndexController() throws Exception {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity("/", String.class);

        System.out.println("StatusCode -> " + responseEntity.getStatusCode() + ",responseBody -> " + responseEntity.getBody());
    }

    @Test
    public void testUserController() throws Exception {
        ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(
                "/users/{mobile}",
                String.class,
                "18516291668"
        );

        System.out.println("StatusCode -> " + responseEntity.getStatusCode() + ",responseBody -> " + responseEntity.getBody());
    }

}
