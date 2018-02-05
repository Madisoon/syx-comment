package com.syx.comment.entity;

import javax.persistence.*;

/**
 * 描述:
 * 角色用户的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:36
 */
@Entity
@Table(name = "sys_role_user")
public class SysRoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "gmt_create")
    private String gmtCreate;

    @Column(name = "gmt_modified")
    private String gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }
}
