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

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nick_name")
    private String userNickName;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_pwd")
    private String userPwd;

    @Column(name = "user_dep")
    private String userDep;

    @Column(name = "user_packet_no")
    private String userPacketNo;

    @Column(name = "user_create_time")
    private Date userCreateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserDep() {
        return userDep;
    }

    public void setUserDep(String userDep) {
        this.userDep = userDep;
    }

    public String getUserPacketNo() {
        return userPacketNo;
    }

    public void setUserPacketNo(String userPacketNo) {
        this.userPacketNo = userPacketNo;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }
}
