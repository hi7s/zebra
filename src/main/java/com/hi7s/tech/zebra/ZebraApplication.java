package com.hi7s.tech.zebra;

import com.hi7s.tech.zebra.service.RobotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ZebraApplication implements ApplicationRunner {

    @Autowired
    private RobotService robotService;

    public static void main(String[] args) {
        SpringApplication.run(ZebraApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        robotService.generate();
    }
}