package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 系统用户的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:39
 */
@Entity
@Table(name = "sys_user")
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_token")
    private String userToken;

    @Column(name = "user_account")
    private String userAccount;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "is_using")
    private int isUsing = 1;

    @Column(name = "user_integration")
    private int userIntegration;

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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(int isUsing) {
        this.isUsing = isUsing;
    }

    public int getUserIntegration() {
        return userIntegration;
    }

    public void setUserIntegration(int userIntegration) {
        this.userIntegration = userIntegration;
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
