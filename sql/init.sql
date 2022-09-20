
use demo;

CREATE TABLE `m_user`
(
    `id`          bigint(20) primary key AUTO_INCREMENT COMMENT '表id',
    `uid`         bigint(20) NOT NULL COMMENT '用户id',
    `name`        varchar(45)  NOT NULL DEFAULT '',
    `email`       varchar(128) NOT NULL,
    `crete_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据的创建时间',
    `modify_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据的修改时间',
    `is_del`      tinyint(2) NOT NULL DEFAULT '0' COMMENT '这条数据是否被删除的标志，0-正常，1-删除',
    `email_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT 'email是否发送，0-未发，1-已发',
    `send_time`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '邮件时间',
    UNIQUE KEY `uniq_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;