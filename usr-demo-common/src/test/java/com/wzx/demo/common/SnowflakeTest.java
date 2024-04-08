package com.wzx.demo.common;

import org.junit.Assert;
import org.junit.Test;

public class SnowflakeTest {

    private long workId = 1;

    @Test
    public void nextId() {
        Snowflake snowflake = Snowflake.create(workId);
        Assert.assertTrue(snowflake.nextId() > 0);
    }

    @Test
    public void testChange() {
        System.out.println("workId = " + Integer.toBinaryString(18));
        System.out.println("workId = " + Integer.toBinaryString(25));
        System.out.println("workId = " + Integer.toBinaryString(37));
    }
}