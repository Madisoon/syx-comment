package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 任务发布适用部门的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:39
 */

@Entity
@Table(name = "sys_task_release_user")
public class SysTaskReleaseUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_release_id")
    private Long taskReleaseId;

    @Column(name = "receiver_account")
    private String receiverAccount;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modified")
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskReleaseId() {
        return taskReleaseId;
    }

    public void setTaskReleaseId(Long taskReleaseId) {
        this.taskReleaseId = taskReleaseId;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
