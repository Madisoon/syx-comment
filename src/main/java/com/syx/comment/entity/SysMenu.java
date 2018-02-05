package com.syx.comment.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * 菜单的entiy类
 *
 * @author Msater Zg
 * @create 2017-11-11 10:29
 */
@Entity
@Table(name = "sys_menu")
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_type")
    private String menuType;

    @Column(name = "menu_pid")
    private Long menuPid;

    @Column(name = "menu_content")
    private String menuContent;

    @Column(name = "menu_attr")
    private String menuAttr;

    @Column(name = "menu_sort")
    private int menuSort;

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Long getMenuPid() {
        return menuPid;
    }

    public void setMenuPid(Long menuPid) {
        this.menuPid = menuPid;
    }

    public String getMenuContent() {
        return menuContent;
    }

    public void setMenuContent(String menuContent) {
        this.menuContent = menuContent;
    }

    public String getMenuAttr() {
        return menuAttr;
    }

    public void setMenuAttr(String menuAttr) {
        this.menuAttr = menuAttr;
    }

    public int getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(int menuSort) {
        this.menuSort = menuSort;
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
