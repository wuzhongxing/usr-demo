package com.wzx.demo.common;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snowflake {

    private static final Logger LOGGER = LoggerFactory.getLogger(Snowflake.class);

    private final long workerId;

    private final DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private final DateTime dateTime = fmt.parseDateTime("2018-01-01 00:00:00");

    /**
     * 时间起始标记点， 作为基准， 一般取系统的最近时间， 默认2017-01-01
     */
    private final long epoch = dateTime.getMillis();

    /**
     * 机器id 所占的位数
     */
    private static final long workerIdBits = 10L;

    /**
     * 机器id最大值
     */
    private static final long maxWorkerId = -1L ^ -1L << workerIdBits;

    /**
     * 序列在id中占的位数
     */
    private static final Long sequenceBits = 12L;

    /**
     * 机器id向左移12位
     */
    private static final long workerIdShift = sequenceBits;

    /**
     * 时间戳向左移22位
     */
    private static final long timestampLeftShift = sequenceBits + workerIdBits;

    /**
     * 生成序列的掩码，4095, 12位
     */
    private static final long sequenceMask = -1L ^ -1L << sequenceBits;


    /**
     * 并发控制, 毫秒内序列(0-4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成id的时间戳
     */
    private long lastTimestamp = -1L;

    protected Snowflake(long workerId) {
        if (workerId > maxWorkerId || workerId < 0) {
            String message = String.format("worker id grater than %d or less than 0", maxWorkerId);
            throw new IllegalArgumentException(message);
        }

        this.workerId = workerId;
    }

    public static Snowflake create(long workerId) {
        return new Snowflake(workerId);
    }

    public synchronized long nextId() {

        long timestamp = timeGen();

        if (this.lastTimestamp == timestamp) {
            this.sequence = sequence + 1 & sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException("clock moved backwards Exception");
        }

        this.lastTimestamp = timestamp;

        return timestamp - this.epoch << timestampLeftShift | this.workerId << workerIdShift | this.sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}