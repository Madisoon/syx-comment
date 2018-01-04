package com.syx.comment.entity;

import javax.persistence.*;

/**
 * 描述:
 * 任务已读标记entiy
 *
 * @author Msater Zg
 * @create 2017-12-29 16:49
 */
@Entity
@Table(name = "sys_read_tab")
public class SysReadTab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "user_name")
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
