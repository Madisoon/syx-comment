package com.syx.comment.entity;

import javax.persistence.*;

/**
 * 描述:已完成标记entiy类
 *
 * @author Msater Zg
 * @create 2017-12-29 16:57
 */
@Entity
@Table(name = "sys_finish_tab")
public class SysFinishTab {
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
