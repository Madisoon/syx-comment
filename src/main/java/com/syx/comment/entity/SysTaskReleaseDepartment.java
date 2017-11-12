package com.syx.comment.entity;

import javax.persistence.*;

/**
 * 描述:
 * 任务发布适用部门的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:39
 */

@Entity
@Table(name = "sys_task_release_department")
public class SysTaskReleaseDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_release_id")
    private Long taskReleaseId;

    @Column(name = "dep_no")
    private Long depNo;

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

    public Long getDepNo() {
        return depNo;
    }

    public void setDepNo(Long depNo) {
        this.depNo = depNo;
    }
}
