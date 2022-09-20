package com.wzx.demo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Long id;

    private Long uid;

    private String name;

    private String email;

    private Date creteTime;

    private Date modifyTime;

    private Integer isDel;

    private Integer emailStatus;

    private Date sendTime;
}