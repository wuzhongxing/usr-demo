package com.wzx.demo;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppTest.App.class)
@MapperScan("com.wzx.demo.mapper")
@Ignore
public class BaseTest {
}
