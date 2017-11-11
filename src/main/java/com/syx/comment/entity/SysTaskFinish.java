package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 任务完成的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:37
 */
@Entity
@Table(name = "sys_task_finish")
public class SysTaskFinish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String taskName;

    private int taskType;

    private String taskLink;

    private String taskTitle;

    private String taskSite;

    private String taskNickName;

    private String taskContent;

    private String taskFileUrl;

    private String taskImageUrl;

    private String taskExplain;

    private int taskStaus;

    private int taskMark;

    private String taskFeedback;

    private Date taskCreateTime;

    private String taskCreater;

    private Long taskPacketNo;
}
