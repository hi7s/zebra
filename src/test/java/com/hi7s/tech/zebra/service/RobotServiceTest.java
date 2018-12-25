package com.hi7s.tech.zebra.service;

import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotServiceTest {

    @Autowired
    private RobotService robotService;

    @Test
    public void generate() throws IOException, TemplateException {
        robotService.generate();
    }
}