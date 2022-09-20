package com.wzx.demo;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppTest.App.class)
@MapperScan("com.wzx.demo.mapper")
public class AppTest extends TestCase {

    @SpringBootApplication
    @EnableTransactionManagement
    public static class App {
        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }
    }

    @Test
    public void initTest() {
        System.out.println("start test");
    }
}
